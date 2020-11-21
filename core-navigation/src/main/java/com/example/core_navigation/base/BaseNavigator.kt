package com.example.core_navigation.base

import com.example.feature_pokemonlist.ui.MainFragment

interface BaseNavigator {
    fun toMainFragment(): MainFragment
}