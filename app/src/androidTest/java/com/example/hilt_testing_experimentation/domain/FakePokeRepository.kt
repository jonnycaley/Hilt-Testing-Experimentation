package com.example.hilt_testing_experimentation.domain

import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemon
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonList
import io.reactivex.rxjava3.core.Single

class FakePokeRepository: PokeRepository {
    override fun getPokemonList(offset: Int): Single<PokemonList> {
        return Single.error(Exception())
    }
    override fun getDetailedPokemon(name: String): Single<DetailedPokemon> {
        return Single.error(Exception())
    }
}