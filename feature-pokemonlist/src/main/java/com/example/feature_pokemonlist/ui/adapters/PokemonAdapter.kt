package com.example.feature_pokemonlist.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.R
import com.example.feature_pokemonlist.databinding.ItemPokemonBinding
import com.example.core.di.analytics.Analytics
import com.example.feature_pokemonlist.di.imageloader.ImageLoader
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import javax.inject.Inject

class PokemonAdapter @Inject constructor(
    private val analytics: Analytics,
    private val imageLoader: ImageLoader,
    private val pokemonListNavigator: PokemonListNavigator
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val pokemon: MutableList<DetailedPokemon> = mutableListOf()

    lateinit var activity: Activity

    fun updateItems(list: List<DetailedPokemon>, activity: Activity) {
        this.activity = activity
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

    inner class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: DetailedPokemon) {
            binding.name.text = pokemon.name
            pokemon.imageUrl?.let { imageUrl ->
                imageLoader.loadImage(
                    context = binding.root.context,
                    loadable = imageUrl,
                    placeholder = R.drawable.placeholder,
                    imageView = binding.image,
                    onResourceReady = { pokemon.name?.let { analytics.logImageView(it) } }
                )
            }

            ViewCompat.setTransitionName(binding.image, pokemon.name)

            binding.root.setOnClickListener {
                pokemonListNavigator.toPokemonDetail(activity, pokemon, absoluteAdapterPosition, binding.image)
            }
        }
    }
}