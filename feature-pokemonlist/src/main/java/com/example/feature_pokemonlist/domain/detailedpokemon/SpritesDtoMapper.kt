package com.example.feature_pokemonlist.domain.detailedpokemon

import com.example.core.domain.Sprites
import com.example.core.data.model.detailedpokemondto.SpritesDto
import javax.inject.Inject

class SpritesDtoMapper @Inject constructor() {
    fun map(dto: SpritesDto?): Sprites {
        return Sprites(dto?.frontDefault)
    }
}