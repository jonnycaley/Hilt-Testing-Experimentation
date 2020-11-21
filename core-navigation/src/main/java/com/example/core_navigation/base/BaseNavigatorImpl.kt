package com.example.core_navigation.base

import com.example.feature_pokemonlist.ui.MainFragment

class BaseNavigatorImpl : BaseNavigator {
    override fun toMainFragment(): MainFragment {
        return MainFragment.newInstance()
    }
}