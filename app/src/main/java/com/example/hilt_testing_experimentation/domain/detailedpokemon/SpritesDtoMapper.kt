package com.example.hilt_testing_experimentation.domain.detailedpokemon

import com.example.hilt_testing_experimentation.data.model.detailedpokemondto.SpritesDto
import javax.inject.Inject

class SpritesDtoMapper @Inject constructor() {
    fun map(dto: SpritesDto?): Sprites {
        return Sprites(dto?.frontDefault)
    }
}