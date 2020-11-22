package com.example.feature_pokemonlist.domain

import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonList
import io.reactivex.rxjava3.core.Single

class FakePokeRepository: PokeRepository {

    private val getPokemonListResponses: MutableList<Single<PokemonList>> = mutableListOf()
    private val getDetailedPokemonResponses: MutableMap<String, Single<DetailedPokemon>> = mutableMapOf()

    override fun getPokemonList(offset: Int): Single<PokemonList> {
        if (getPokemonListResponses.isEmpty())
            throw Exception("Haven't set getPokemonList response")
        return getPokemonListResponses.removeAt(0)
    }

    fun addGetPokemonListResponse(response: Single<PokemonList>) {
        getPokemonListResponses.add(response)
    }

    override fun getDetailedPokemon(name: String): Single<DetailedPokemon> {
        getDetailedPokemonResponses[name]?.let {
            return it
        } ?: throw Exception("Haven't set getDetailedPokemon response")
    }

    fun addGetDetailedPokemonResponse(pokemon: String, response: Single<DetailedPokemon>) {
        getDetailedPokemonResponses[pokemon] = response
    }
}