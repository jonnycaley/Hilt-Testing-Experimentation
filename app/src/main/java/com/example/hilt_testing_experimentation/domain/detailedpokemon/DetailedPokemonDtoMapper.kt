package com.example.hilt_testing_experimentation.domain.detailedpokemon

import com.example.hilt_testing_experimentation.data.model.detailedpokemondto.DetailedPokemonDto
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
