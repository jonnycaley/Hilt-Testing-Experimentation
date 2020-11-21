package com.example.core_navigation.feature_pokemonlist

import android.content.Context
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemondetail.PokemonDetailActivity
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import javax.inject.Inject

class PokemonListNavigatorImpl @Inject constructor(): PokemonListNavigator {
    override fun toPokemonDetail(context: Context, detailedPokemon: DetailedPokemon) {
        PokemonDetailActivity.start(context, detailedPokemon)
    }
}