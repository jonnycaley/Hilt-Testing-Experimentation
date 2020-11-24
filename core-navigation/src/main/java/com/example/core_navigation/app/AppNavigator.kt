package com.example.core_navigation.app

import com.example.feature_pokemonlist.ui.MainFragment

interface AppNavigator {
    fun toMainFragment(): MainFragment
}