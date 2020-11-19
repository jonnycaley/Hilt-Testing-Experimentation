package com.example.feature_pokemonlist.domain.pokemonlist

import com.example.core.data.model.pokemonlistdto.PokemonDto
import javax.inject.Inject

class PokemonDtoMapper @Inject constructor() {
    fun map(dto: PokemonDto): Pokemon {
        return Pokemon(
            dto.name,
            dto.url
        )
    }
}