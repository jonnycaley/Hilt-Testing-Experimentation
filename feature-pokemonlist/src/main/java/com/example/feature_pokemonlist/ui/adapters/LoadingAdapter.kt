package com.example.feature_pokemonlist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_pokemonlist.databinding.ItemLoadingBinding

class LoadingAdapter(private val listener: VisibilityListener) : RecyclerView.Adapter<LoadingAdapter.ViewHolder>() {

    var nextPageOffset: Int? = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =  ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = nextPageOffset?.let { 1 } ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        nextPageOffset?.let {
            listener.isVisible(it)
        }
    }

    class ViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)

    interface VisibilityListener {
        fun isVisible(nextPage: Int)
    }
}