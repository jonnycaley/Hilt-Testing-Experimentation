package com.example.hilt_testing_experimentation.domain

import com.example.hilt_testing_experimentation.data.PokeApiService
import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemon
import com.example.hilt_testing_experimentation.domain.detailedpokemon.DetailedPokemonDtoMapper
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonList
import com.example.hilt_testing_experimentation.domain.pokemonlist.PokemonListDtoMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class PokeRepository @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonListDtoMapper: PokemonListDtoMapper,
    private val detailedPokemonDtoMapper: DetailedPokemonDtoMapper
) {
    open fun getPokemonList(offset: Int): Single<PokemonList> {
        return pokeApiService.getPokemon(offset)
            .map { pokemonListDtoMapper.map(it) }
    }
    open fun getDetailedPokemon(name: String): Single<DetailedPokemon> {
        return pokeApiService.getDetailedPokemon(name)
            .map { detailedPokemonDtoMapper.map(it) }
    }
}