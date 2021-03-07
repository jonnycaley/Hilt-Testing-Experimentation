package com.example.core_navigation.app

import com.example.feature_pokemonlist.ui.PokemonListFragment

interface AppNavigator {
    fun toMainFragment(): PokemonListFragment
}