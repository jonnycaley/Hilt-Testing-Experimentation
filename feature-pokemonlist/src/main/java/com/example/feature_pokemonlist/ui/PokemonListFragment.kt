package com.example.feature_pokemonlist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.viewBinding
import com.example.core.visibleIf
import com.example.feature_pokemonlist.R
import com.example.feature_pokemonlist.databinding.MainFragmentBinding
import com.example.core.di.analytics.Analytics
import com.example.feature_pokemonlist.ui.adapters.LoadingAdapter
import com.example.feature_pokemonlist.ui.adapters.PokemonAdapter
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: PokemonListViewModel by viewModels()

    private val binding by viewBinding(MainFragmentBinding::bind)

    private lateinit var loadingAdapter: LoadingAdapter
    private lateinit var concatAdapter: ConcatAdapter

    @Inject
    lateinit var pokemonListNavigator: PokemonListNavigator

    @Inject
    lateinit var pokemonAdapterFactory: PokemonAdapter.Factory
    private val pokemonAdapter: PokemonAdapter by lazy {
        pokemonAdapterFactory.create { pokemon, imageView, textView ->
            pokemonListNavigator.toPokemonDetail(requireActivity(), pokemon, imageView, textView)
        }
    }

    @Inject
    lateinit var analytics: Analytics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        binding.error.setOnClickListener { viewModel.loadPokemon() }

        analytics.logScreenView("Main fragment")

        viewModel.nextPageOffset.observe(viewLifecycleOwner) { nextPage ->
            loadingAdapter.nextPageOffset = nextPage
            if (nextPage == null) {
                loadingAdapter.notifyItemRemoved(0)
            }
        }

        viewModel.pokemon.observe(viewLifecycleOwner) { response ->
            binding.recyclerMain visibleIf response.isSuccess()
            binding.progress visibleIf response.isLoading()
            binding.error visibleIf response.isError()

            if (response.isSuccess()) {
                pokemonAdapter.updateItems(response.data ?: emptyList())
            }
        }
    }

    private fun setupRecycler() {
        loadingAdapter = LoadingAdapter(object: LoadingAdapter.VisibilityListener {
            override fun isVisible(nextPage: Int) {
                viewModel.loadMorePokemon(nextPage)
            }
        })
        concatAdapter = ConcatAdapter(pokemonAdapter, loadingAdapter)

        with(binding.recyclerMain) {
            adapter = concatAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (adapter?.getItemViewType(position) == concatAdapter.adapters.size - 1) // is last item view type in the mergeadapter
                            2
                        else
                            1
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = PokemonListFragment()
    }
}