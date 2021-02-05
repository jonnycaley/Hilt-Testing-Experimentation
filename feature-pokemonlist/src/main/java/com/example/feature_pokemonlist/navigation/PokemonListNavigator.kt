package com.example.feature_pokemonlist.navigation

import android.app.Activity
import android.widget.ImageView
import com.example.core.domain.DetailedPokemon

interface PokemonListNavigator {
    fun toPokemonDetail(
        activity: Activity,
        detailedPokemon: DetailedPokemon,
        adapterPosition: Int,
        imageView: ImageView
    )
}