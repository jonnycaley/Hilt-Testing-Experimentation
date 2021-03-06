package com.example.feature_pokemonlist.domain

import com.example.core.domain.DetailedPokemon
import com.example.core.data.PokeApiService
import com.example.feature_pokemonlist.domain.detailedpokemon.DetailedPokemonDtoMapper
import com.example.feature_pokemonlist.domain.pokemonlist.Pokemon
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonList
import com.example.feature_pokemonlist.domain.pokemonlist.PokemonListDtoMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class PokeRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonListDtoMapper: PokemonListDtoMapper,
    private val detailedPokemonDtoMapper: DetailedPokemonDtoMapper
) : PokeRepository {
    override fun getPokemonList(offset: Int): Single<PokemonList> {
        return pokeApiService.getPokemon(offset)
            .map { pokemonListDtoMapper.map(it) }
    }
    override fun getDetailedPokemon(pokemon: Pokemon): Single<DetailedPokemon> {
        return pokeApiService.getDetailedPokemon(pokemon.name)
            .map { detailedPokemonDtoMapper.map(it, pokemon.url) }
    }
}