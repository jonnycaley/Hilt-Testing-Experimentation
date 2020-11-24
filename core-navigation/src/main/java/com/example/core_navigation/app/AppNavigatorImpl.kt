package com.example.core_navigation.app

import com.example.feature_pokemonlist.ui.MainFragment
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(): AppNavigator {
    override fun toMainFragment(): MainFragment {
        return MainFragment.newInstance()
    }
}