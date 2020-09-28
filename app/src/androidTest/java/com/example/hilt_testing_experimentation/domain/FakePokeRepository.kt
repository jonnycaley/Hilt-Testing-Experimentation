package com.example.hilt_testing_experimentation.domain

import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemon
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonList
import io.reactivex.rxjava3.core.Single

class FakePokeRepository: PokeRepository {

    private val getPokemonListResponses: MutableList<Single<PokemonList>> = mutableListOf()
    private val getDetailedPokemonResponses: MutableList<Single<DetailedPokemon>> = mutableListOf()

    override fun getPokemonList(offset: Int): Single<PokemonList> {
        if (getPokemonListResponses.isEmpty())
            throw Exception("Haven't set getPokemonList response")
        return getPokemonListResponses.removeAt(0)
    }

    override fun getDetailedPokemon(name: String): Single<DetailedPokemon> {
        if (getDetailedPokemonResponses.isEmpty())
            throw Exception("Haven't set getDetailedPokemon response")
        return getDetailedPokemonResponses.removeAt(0)
    }

    fun addGetPokemonListResponse(response: Single<PokemonList>) {
        getPokemonListResponses.add(response)
    }

    fun addGetDetailedPokemonResponses(response: Single<DetailedPokemon>) {
        getDetailedPokemonResponses.add(response)
    }
}