package com.example.feature_pokemondetail

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.core.di.analytics.Analytics
import com.example.core.di.analytics.AnalyticsModule
import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemondetail.di.analytics.FakeAnalytics
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

@UninstallModules(AnalyticsModule::class)
@HiltAndroidTest
class PokemonDetailActivityTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    private val fakeAnalytics: FakeAnalytics = FakeAnalytics()

    @BindValue @JvmField
    val analytics: Analytics = fakeAnalytics

    @Before
    fun before() {
        rule.inject()
    }

    @Test
    fun whenActivityCreated_thenScreenViewRecorded() {

        val activityScenario = launchActivity()

        assertEquals(fakeAnalytics.screenViews.size, 1)
    }

    private fun launchActivity(): Any {
        val intent = Intent(ApplicationProvider.getApplicationContext(), PokemonDetailActivity::class.java)
        intent.putExtra(PokemonDetailActivity.EXTRA_POKEMON, DetailedPokemon("pikachu", "pikachu.com"))
        intent.putExtra(PokemonDetailActivity.EXTRA_IMAGE_TRANSITION_NAME, "pikachu-image")
        intent.putExtra(PokemonDetailActivity.EXTRA_TEXT_TRANSITION_NAME, "pikachu-text")
        return ActivityScenario.launch<PokemonDetailActivity>(intent)
    }
}