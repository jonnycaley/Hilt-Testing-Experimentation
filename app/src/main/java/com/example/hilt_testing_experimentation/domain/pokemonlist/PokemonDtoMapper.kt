package com.example.hilt_testing_experimentation.domain.pokemonlist

import com.example.hilt_testing_experimentation.data.model.pokemonlistdto.PokemonDto
import javax.inject.Inject

class PokemonDtoMapper @Inject constructor() {
    fun map(dto: PokemonDto): Pokemon {
        return Pokemon(
            dto.name,
            dto.url
        )
    }
}