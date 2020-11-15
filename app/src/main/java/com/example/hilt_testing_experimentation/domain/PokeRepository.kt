package com.example.hilt_testing_experimentation.domain

import com.example.core.domain.detailedpokemon.DetailedPokemon
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonList
import io.reactivex.rxjava3.core.Single

interface PokeRepository {
    fun getPokemonList(offset: Int): Single<PokemonList>
    fun getDetailedPokemon(name: String): Single<DetailedPokemon>
}