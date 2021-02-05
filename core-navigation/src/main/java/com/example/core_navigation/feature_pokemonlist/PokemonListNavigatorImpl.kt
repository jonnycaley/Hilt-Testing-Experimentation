package com.example.core_navigation.feature_pokemonlist

import android.app.Activity
import android.widget.ImageView
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemondetail.PokemonDetailActivity
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import javax.inject.Inject

class PokemonListNavigatorImpl @Inject constructor(): PokemonListNavigator {
    override fun toPokemonDetail(
        activity: Activity,
        detailedPokemon: DetailedPokemon,
        adapterPosition: Int,
        imageView: ImageView
    ) {
        PokemonDetailActivity.start(activity, detailedPokemon, adapterPosition, imageView)
    }
}