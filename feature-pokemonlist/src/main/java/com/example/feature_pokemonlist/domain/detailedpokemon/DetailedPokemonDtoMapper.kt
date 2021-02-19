package com.example.feature_pokemonlist.domain.detailedpokemon

import com.example.core.domain.DetailedPokemon
import com.example.core.data.model.detailedpokemondto.DetailedPokemonDto
import javax.inject.Inject

class DetailedPokemonDtoMapper @Inject constructor(
    val imageUrlMapper: ImageUrlMapper
) {
    fun map(dto: DetailedPokemonDto, url: String): DetailedPokemon {
        return DetailedPokemon(
            name = dto.name?.capitalize() ?: "",
            imageUrl = imageUrlMapper.map(url)
        )
    }
}
