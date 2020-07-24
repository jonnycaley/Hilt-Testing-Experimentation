package com.example.hilt_testing_experimentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt_testing_experimentation.databinding.MainFragmentBinding
import com.example.hilt_testing_experimentation.utils.Status
import com.example.hilt_testing_experimentation.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: MainFragmentBinding? = null

    private val adapter = PokemonAdapter()

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

        viewModel.pokemon.observe(viewLifecycleOwner, { response ->
            binding.recyclerMain visibleIf response.isSuccess()
            binding.text visibleIf !response.isSuccess()
            binding.text.setOnClickListener {}

            when (response.status) {
                Status.SUCCESS -> {
                    adapter.updateItems(response.data ?: emptyList())
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
        with(binding.recyclerMain) {
            adapter = this@MainFragment.adapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}