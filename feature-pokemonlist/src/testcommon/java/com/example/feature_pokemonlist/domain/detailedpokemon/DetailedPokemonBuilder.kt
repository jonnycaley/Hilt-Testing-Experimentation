package com.example.feature_pokemonlist.domain.detailedpokemon

import com.example.core.domain.DetailedPokemon

object DetailedPokemonBuilder {

    fun build(pokemon: String): DetailedPokemon {
        return DetailedPokemon(
            name = pokemon,
            imageUrl = "front-default"
        )
    }

    var pikachu = DetailedPokemon(
        name = "Pikachu",
        imageUrl = "front-default"
    )
}