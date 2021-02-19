package com.example.core_navigation.feature_pokemonlist

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemondetail.PokemonDetailActivity
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import javax.inject.Inject

class PokemonListNavigatorImpl @Inject constructor(): PokemonListNavigator {
    override fun toPokemonDetail(
        activity: Activity,
        detailedPokemon: DetailedPokemon,
        imageView: ImageView,
        name: TextView
    ) {
        PokemonDetailActivity.start(activity, detailedPokemon, imageView, name)
    }
}