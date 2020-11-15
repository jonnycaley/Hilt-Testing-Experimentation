package com.example.hilt_testing_experimentation.domain.detailedpokemon

import com.example.core.domain.detailedpokemon.DetailedPokemon

object DetailedPokemonBuilder {

    fun build(pokemon: String): DetailedPokemon {
        return DetailedPokemon(
            name = pokemon,
            sprites = SpriteBuilder.build
        )
    }

    var pikachu = DetailedPokemon(
        name = "Pikachu",
        sprites = SpriteBuilder.build
    )
}