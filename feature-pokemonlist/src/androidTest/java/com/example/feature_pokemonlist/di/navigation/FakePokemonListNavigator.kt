package com.example.feature_pokemonlist.di.navigation

import android.app.Activity
import android.widget.ImageView
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.navigation.PokemonListNavigator

class FakePokemonListNavigator : PokemonListNavigator {
    override fun toPokemonDetail(
        activity: Activity,
        detailedPokemon: DetailedPokemon,
        imageView: ImageView
    ) {

    }
}