package com.example.hilt_testing_experimentation.ui.main

import com.example.hilt_testing_experimentation.di.analytics.Analytics
import com.example.hilt_testing_experimentation.di.analytics.AnalyticsModule
import com.example.hilt_testing_experimentation.di.analytics.FakeAnalytics
import com.example.hilt_testing_experimentation.di.imageloader.FakeImageLoader
import com.example.hilt_testing_experimentation.di.imageloader.ImageLoader
import com.example.hilt_testing_experimentation.di.imageloader.ImageLoaderModule
import com.example.hilt_testing_experimentation.domain.FakePokeRepository
import com.example.hilt_testing_experimentation.domain.PokeRepository
import com.example.hilt_testing_experimentation.domain.PokeRepositoryModule
import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemonBuilder
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonListBuilder
import com.example.hilt_testing_experimentation.domain.pokemonlist.withNoNextPage
import com.example.hilt_testing_experimentation.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(PokeRepositoryModule::class, ImageLoaderModule::class, AnalyticsModule::class)
@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    private val fakePokeRepository: FakePokeRepository = FakePokeRepository()
    private val fakeImageLoader: FakeImageLoader = FakeImageLoader()
    private val fakeAnalytics: FakeAnalytics = FakeAnalytics()

    @BindValue @JvmField
    val pokeRepository: PokeRepository = fakePokeRepository

    @BindValue @JvmField
    val imageLoader: ImageLoader = fakeImageLoader

    @BindValue @JvmField
    val analytics: Analytics = fakeAnalytics

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
    fun givenError_whenStartFragment_thenErrorTextShown() {
        fakePokeRepository.addGetPokemonListResponse(Single.error(Exception("An exception occurred")))

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkErrorDisplayed()
    }

    @Test
    fun givenDataLoaded_whenStartFragment_thenLoadingHiddenAndDataShown() {
        val pokemonListResponse = PokemonListBuilder.build().withNoNextPage()

        fakePokeRepository.addGetPokemonListResponse(Single.just(pokemonListResponse))

        val pokemon = pokemonListResponse.pokemon.map { it.name }

        pokemon.forEach { name ->
            fakePokeRepository.addGetDetailedPokemonResponse(name, Single.just(DetailedPokemonBuilder.build(name)))
        }

        launchFragmentInHiltContainer<MainFragment>()

        MainFragmentRobot()
            .checkLoadingHidden()
            .checkPokemonDisplayed(pokemon[0])
            .checkPokemonDisplayed(pokemon[1])
            .checkPokemonDisplayed(pokemon[2])
    }

    @Test
    fun givenDataLoaded_whenStartFragment_thenAnalyticsRecorded() {
        val pokemonListResponse = PokemonListBuilder.build().withNoNextPage()

        fakePokeRepository.addGetPokemonListResponse(Single.just(pokemonListResponse))

        val pokemon = pokemonListResponse.pokemon.map { it.name }

        pokemon.forEach { name ->
            fakePokeRepository.addGetDetailedPokemonResponse(name, Single.just(DetailedPokemonBuilder.build(name)))
        }

        launchFragmentInHiltContainer<MainFragment>()

        assert(fakeAnalytics.imageViews.contains(pokemon[0]))
        assert(fakeAnalytics.imageViews.contains(pokemon[1]))
        assert(fakeAnalytics.imageViews.contains(pokemon[2]))
    }
}