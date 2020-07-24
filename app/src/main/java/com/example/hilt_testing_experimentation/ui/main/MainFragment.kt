package com.example.hilt_testing_experimentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt_testing_experimentation.databinding.MainFragmentBinding
import com.example.hilt_testing_experimentation.ui.main.adapters.LoadingAdapter
import com.example.hilt_testing_experimentation.ui.main.adapters.PokemonAdapter
import com.example.hilt_testing_experimentation.utils.Status
import com.example.hilt_testing_experimentation.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding? = null

    lateinit var pokemonAdapter: PokemonAdapter
    lateinit var loadingAdapter: LoadingAdapter
    lateinit var mergeAdapter: MergeAdapter

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()

        viewModel.nextPageOffset.observe(viewLifecycleOwner, { nextPage ->
            loadingAdapter.nextPageOffset = nextPage
            if (nextPage == null) {
                loadingAdapter.notifyItemRemoved(0)
            }
        })

        viewModel.pokemon.observe(viewLifecycleOwner, { response ->
            binding.recyclerMain visibleIf response.isSuccess()
            binding.text visibleIf !response.isSuccess()
            binding.text.setOnClickListener {}

            when (response.status) {
                Status.SUCCESS -> {
                    pokemonAdapter.updateItems(response.data ?: emptyList())
                }
                Status.ERROR -> {
                    binding.text.text = "An error occurred, try again later"
                    binding.text.setOnClickListener { viewModel.loadPokemon() }
                }
                Status.LOADING -> {
                    binding.text.text = "Loading..."
                }
            }
        })
    }
    private fun setupRecycler() {
        pokemonAdapter = PokemonAdapter()
        loadingAdapter = LoadingAdapter(object: LoadingAdapter.VisibilityListener {
            override fun isVisible(nextPage: Int) {
                viewModel.loadMorePokemon(nextPage)
            }
        })
        mergeAdapter = MergeAdapter(pokemonAdapter, loadingAdapter)

        with(binding.recyclerMain) {
            adapter = mergeAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (adapter?.getItemViewType(position) == mergeAdapter.adapters.size - 1) // is last item view type in the mergeadapter
                            2
                        else
                            1
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}