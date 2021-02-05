package com.example.feature_pokemonlist.ui.main

import com.example.core.di.analytics.Analytics
import com.example.core.di.analytics.AnalyticsModule
import com.example.core_test.di.imageloader.FakeImageLoader
import com.example.feature_pokemonlist.domain.PokeRepository
import com.example.feature_pokemonlist.domain.PokeRepositoryModule
import com.example.feature_pokemonlist.domain.detailedpokemon.DetailedPokemonBuilder
import com.example.feature_pokemonlist.di.analytics.FakeAnalytics
import com.example.feature_pokemonlist.domain.FakePokeRepository
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonListBuilder
import com.example.feature_pokemonlist.domain.pokemonlist.withNoNextPage
import com.example.feature_pokemonlist.di.navigation.FakePokemonListNavigator
import com.example.feature_pokemonlist.launchFragmentInHiltContainer
import com.example.feature_pokemonlist.navigation.PokemonListNavigator
import com.example.feature_pokemonlist.ui.MainFragment
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(PokeRepositoryModule::class, AnalyticsModule::class)
@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    private val fakePokeRepository: FakePokeRepository = FakePokeRepository()
    private val fakeAnalytics: FakeAnalytics = FakeAnalytics()
    private val fakeNavigator: FakePokemonListNavigator = FakePokemonListNavigator()

    @BindValue @JvmField
    val pokeRepository: PokeRepository = fakePokeRepository

    @BindValue @JvmField
    val analytics: Analytics = fakeAnalytics

    @Inject
    lateinit var imageLoader: FakeImageLoader

    @BindValue @JvmField
    val navigator: PokemonListNavigator = fakeNavigator

    @Before
    fun before() {
        rule.inject()
    }

    @Test
    fun givenDataLoading_whenStartFragment_thenLoadingShown() {
        fakePokeRepository.addGetPokemonListResponse(Single.never())

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkLoadingDisplayed()
    }

    @Test
    fun givenError_whenStartFragment_thenErrorTextShownAndAnalyticsNotRecorded() {
        fakePokeRepository.addGetPokemonListResponse(Single.error(Exception("An exception occurred")))

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkErrorDisplayed()
    }

    @Test
    fun givenDataLoaded_whenStartFragment_thenLoadingHiddenAndDataShownAndAnalyticsRecorded() {
        val pokemonListResponse = PokemonListBuilder.build().withNoNextPage()

        fakePokeRepository.addGetPokemonListResponse(Single.just(pokemonListResponse))

        val pokemon = pokemonListResponse.pokemon.map { it.name }

        pokemon.forEach { name ->
            fakePokeRepository.addGetDetailedPokemonResponse(name, Single.just(
                DetailedPokemonBuilder.build(name)))
        }

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkLoadingHidden()
            .checkPokemonDisplayed(pokemon[0])
            .checkPokemonDisplayed(pokemon[1])
            .checkPokemonDisplayed(pokemon[2])

        assertEquals(fakeAnalytics.imageViews[0], pokemon[0])
        assertEquals(fakeAnalytics.imageViews[1], pokemon[1])
        assertEquals(fakeAnalytics.imageViews[2], pokemon[2])
    }

    @Test
    fun givenImagesNotLoaded_whenDataLoaded_thenAnalyticsNotRecorded() {
        val pokemonListResponse = PokemonListBuilder.build().withNoNextPage()

        fakePokeRepository.addGetPokemonListResponse(Single.just(pokemonListResponse))

        val pokemon = pokemonListResponse.pokemon.map { it.name }

        pokemon.forEach { name ->
            fakePokeRepository.addGetDetailedPokemonResponse(name, Single.just(DetailedPokemonBuilder.build(name)))
        }

        imageLoader.loadSuccessfully = false

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkPokemonDisplayed(pokemon[0])

        assertEquals(fakeAnalytics.imageViews.size, 0)
    }
}