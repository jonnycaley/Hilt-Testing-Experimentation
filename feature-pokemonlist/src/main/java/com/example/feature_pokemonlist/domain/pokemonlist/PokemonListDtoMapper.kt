package com.example.feature_pokemonlist.domain.pokemonlist

import com.example.feature_pokemonlist.data.model.pokemonlistdto.PokemonListDto
import javax.inject.Inject

class PokemonListDtoMapper @Inject constructor(
    private val pokemonDtoMapper: PokemonDtoMapper
) {
    fun map(dto: PokemonListDto): PokemonList {
        return PokemonList(
            dto.next,
            dto.results.map { pokemonDtoMapper.map(it) }
        )
    }
}