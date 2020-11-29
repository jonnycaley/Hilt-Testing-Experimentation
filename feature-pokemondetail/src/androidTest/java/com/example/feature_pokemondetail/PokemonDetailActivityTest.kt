package com.example.feature_pokemondetail

import androidx.test.core.app.ActivityScenario
import com.example.core.di.analytics.Analytics
import com.example.core.di.analytics.AnalyticsModule
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
    fun useAppContext() {

        val activityScenario = ActivityScenario.launch(PokemonDetailActivity::class.java)

        assertEquals(fakeAnalytics.screenViews.size, 1)
    }
}