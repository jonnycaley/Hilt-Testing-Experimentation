package com.example.hilt_testing_experimentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt_testing_experimentation.databinding.MainFragmentBinding
import com.example.hilt_testing_experimentation.utils.Status
import com.example.hilt_testing_experimentation.utils.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private var _binding: MainFragmentBinding? = null

    private val adapter = StocksAdapter()

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

        viewModel.summary.observe(viewLifecycleOwner, { response ->
            binding.recyclerMain visibleIf response.isSuccess()
            binding.text visibleIf !response.isSuccess()

            binding.text.setOnClickListener {}

            when (response.status) {
                Status.SUCCESS -> {
                    adapter.updateItems(response.data?.response?.stocks ?: emptyList())
                }
                Status.ERROR -> {
                    binding.text.text = "An error occurred, try again later"
                    binding.text.setOnClickListener { viewModel.retry() }
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
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}