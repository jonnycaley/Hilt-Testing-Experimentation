package com.example.feature_pokemonlist.domain

import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonList
import io.reactivex.rxjava3.core.Single

interface PokeRepository {
    fun getPokemonList(offset: Int): Single<PokemonList>
    fun getDetailedPokemon(name: String): Single<DetailedPokemon>
}