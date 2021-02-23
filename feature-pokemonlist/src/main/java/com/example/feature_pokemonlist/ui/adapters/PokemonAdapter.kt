package com.example.feature_pokemonlist.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.R
import com.example.feature_pokemonlist.databinding.ItemPokemonBinding
import com.example.core.di.analytics.Analytics
import com.example.core.di.imageloader.ImageLoader
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class PokemonAdapter @Inject constructor(
    private val analytics: Analytics,
    private val imageLoader: ImageLoader,
    private val pokemonListNavigator: PokemonListNavigator
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var pokemonList: MutableList<DetailedPokemon> = mutableListOf()

    lateinit var activity: Activity

    fun updateItems(pokemon: List<DetailedPokemon>, activity: Activity) {
        this.activity = activity
        val oldPokemonListSize = pokemonList.size
        val newPokemon = pokemon.subList(oldPokemonListSize, pokemon.size)
        pokemonList.addAll(newPokemon)
        notifyItemRangeInserted(oldPokemonListSize, newPokemon.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding, onItemClick)
    }

    private val onItemClick = { position: Int, imageView: ImageView, textView: TextView ->
        pokemonListNavigator.toPokemonDetail(activity, pokemonList[position], imageView, textView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding, onItemClick: (Int, ImageView, TextView) -> Unit): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick(bindingAdapterPosition, binding.image, binding.name)
            }
        }
        fun bind(pokemon: DetailedPokemon) {
            with(pokemon) {
                binding.name.text = name
                imageLoader.loadImage(
                    context = binding.root.context,
                    loadable = imageUrl,
                    placeholder = R.drawable.placeholder,
                    imageView = binding.image,
                    onResourceReady = { analytics.logImageView(name) }
                )
            }
        }
    }
}