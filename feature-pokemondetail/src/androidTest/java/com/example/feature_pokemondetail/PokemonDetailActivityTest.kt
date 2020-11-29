package com.example.feature_pokemondetail

import androidx.test.core.app.ActivityScenario
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonDetailActivityTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Before
    fun before() {
        rule.inject()
    }

    @Test
    fun useAppContext() {

        val activityScenario = ActivityScenario.launch(PokemonDetailActivity::class.java)
    }
}