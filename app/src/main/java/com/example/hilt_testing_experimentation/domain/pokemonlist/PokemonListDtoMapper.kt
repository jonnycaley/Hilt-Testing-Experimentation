package com.example.hilt_testing_experimentation.domain.pokemonlist

import com.example.hilt_testing_experimentation.data.model.pokemonlistdto.PokemonListDto
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