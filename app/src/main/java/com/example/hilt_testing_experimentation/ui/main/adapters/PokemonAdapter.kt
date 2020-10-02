package com.example.hilt_testing_experimentation.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hilt_testing_experimentation.R
import com.example.hilt_testing_experimentation.databinding.ItemPokemonBinding
import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemon

class PokemonAdapter(private val pokemon: MutableList<DetailedPokemon> = mutableListOf()) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    fun updateItems(list: List<DetailedPokemon>) {
        pokemon.clear()
        pokemon.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemon[position])
    }

    class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: DetailedPokemon) {
            binding.name.text = pokemon.name
            Glide
                .with(binding.root.context)
                .load(pokemon.sprites?.frontDefault)
                .error(R.drawable.placeholder)
                .into(binding.image)
        }
    }
}