package com.example.core_navigation.app

import com.example.feature_pokemonlist.ui.PokemonListFragment
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(): AppNavigator {
    override fun toMainFragment(): PokemonListFragment {
        return PokemonListFragment.newInstance()
    }
}
