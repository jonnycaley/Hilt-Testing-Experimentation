package com.example.hilt_testing_experimentation.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hilt_testing_experimentation.data.model.detailedpokemon.DetailedPokemonDto
import com.example.hilt_testing_experimentation.databinding.ItemPokemonBinding

class PokemonAdapter(private val pokemon: MutableList<DetailedPokemonDto> = mutableListOf()) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    fun updateItems(list: List<DetailedPokemonDto>) {
        pokemon.clear()
        pokemon.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemon[position])
    }

    class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: DetailedPokemonDto) {
            binding.name.text = pokemon.name
            Glide
                .with(binding.root.context)
                .load(pokemon.sprites?.frontDefault)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.image)
        }
    }
}