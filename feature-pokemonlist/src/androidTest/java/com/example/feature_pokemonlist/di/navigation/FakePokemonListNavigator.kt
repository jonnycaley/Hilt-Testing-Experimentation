package com.example.feature_pokemonlist.di.navigation

import android.content.Context
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.navigation.PokemonListNavigator

class FakePokemonListNavigator : PokemonListNavigator {
    override fun toPokemonDetail(context: Context, detailedPokemon: DetailedPokemon) {

    }
}