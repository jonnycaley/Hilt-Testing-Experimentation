package com.example.feature_pokemonlist.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.feature_pokemonlist.domain.PokeRepository
import com.example.feature_pokemonlist.domain.detailedpokemon.DetailedPokemonBuilder
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonListBuilder
import com.example.core.utils.TestSchedulers
import com.example.feature_pokemonlist.domain.pokemonlist.withOnePokemon
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception

class MainViewModelTest {

    private val mockPokeRepository: PokeRepository = mock(PokeRepository::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when init, then resource loading`() {
        `when`(mockPokeRepository.getPokemonList(0)).thenReturn(
            Single.never()
        )
        val viewModel = testViewModel()

        viewModel.pokemon.observeForever {
            assert(it.isLoading())
        }
    }

    @Test
    fun `given error, when init, then resource error`() {
        `when`(mockPokeRepository.getPokemonList(0)).thenReturn(
            Single.error(Exception())
        )
        val viewModel = testViewModel()

        viewModel.pokemon.observeForever {
            assert(it.isError())
        }
    }

    @Test
    fun `when load pokemon success, then resource success`() {
        `when`(mockPokeRepository.getPokemonList(0)).thenReturn(
            Single.just(PokemonListBuilder.build().withOnePokemon())
        )
        `when`(mockPokeRepository.getDetailedPokemon("pokemon-name-0")).thenReturn(
            Single.just(DetailedPokemonBuilder.pikachu)
        )

        val viewModel = testViewModel()

        viewModel.nextPageOffset.observeForever {
            assert(it!! == 20)
        }
        viewModel.pokemon.observeForever {
            assert(it.isSuccess())
            assert(it.data == listOf(DetailedPokemonBuilder.pikachu))
        }
    }

    private fun testViewModel(): com.example.feature_pokemonlist.ui.MainViewModel {
        return com.example.feature_pokemonlist.ui.MainViewModel(
            pokeRepository = mockPokeRepository,
            schedulers = TestSchedulers.get()
        )
    }
}