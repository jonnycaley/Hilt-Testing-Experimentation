package com.example.core_navigation.base

import com.example.feature_pokemonlist.ui.MainFragment

interface AppNavigator {
    fun toMainFragment(): MainFragment
}