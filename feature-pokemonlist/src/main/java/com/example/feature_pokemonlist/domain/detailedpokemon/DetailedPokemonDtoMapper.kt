package com.example.feature_pokemonlist.domain.detailedpokemon

import com.example.core.domain.DetailedPokemon
import com.example.feature_pokemonlist.data.model.detailedpokemondto.DetailedPokemonDto
import javax.inject.Inject

class DetailedPokemonDtoMapper @Inject constructor(
    val spritesDtoMapper: SpritesDtoMapper
) {
    fun map(dto: DetailedPokemonDto): DetailedPokemon {
        return DetailedPokemon(
            name = dto.name,
            sprites = spritesDtoMapper.map(dto.spritesDto)
        )
    }
}
