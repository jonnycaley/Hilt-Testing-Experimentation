package com.example.feature_pokemonlist.navigation

import android.content.Context
import com.example.core.domain.DetailedPokemon

interface PokemonListNavigator {
    fun toPokemonDetail(context: Context, detailedPokemon: DetailedPokemon)
}