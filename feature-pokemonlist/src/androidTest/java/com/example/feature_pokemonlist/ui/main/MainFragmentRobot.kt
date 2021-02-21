package com.example.feature_pokemonlist.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.feature_pokemonlist.R
import org.hamcrest.CoreMatchers.not

class MainFragmentRobot {

    fun checkErrorDisplayed(): MainFragmentRobot {
        onView(withId(R.id.error))
            .check(matches(isDisplayed()))
        return this
    }

    fun checkLoadingDisplayed(): MainFragmentRobot {
        return checkLoadingVisibility(true)
    }

    fun checkLoadingHidden(): MainFragmentRobot {
        return checkLoadingVisibility(false)
    }

    fun checkPokemonDisplayed(name: String): MainFragmentRobot {
        onView(withId(R.id.recycler_main))
            .check(matches(hasDescendant(withText(name))))
        return this
    }

    private fun checkLoadingVisibility(isVisible: Boolean): MainFragmentRobot {
        onView(withId(R.id.progress))
            .check(matches(if (isVisible) isDisplayed() else not(isDisplayed())))
        return this
    }
}