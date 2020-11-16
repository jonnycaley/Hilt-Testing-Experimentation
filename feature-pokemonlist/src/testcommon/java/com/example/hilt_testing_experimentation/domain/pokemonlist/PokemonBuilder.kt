package com.example.hilt_testing_experimentation.domain.pokemonlist

import com.example.feature_pokemonlist.domain.pokemonlist.Pokemon

object PokemonBuilder {

    fun buildPokemon(index: Int): Pokemon {
        return Pokemon(
            name = "pokemon-name-$index",
            url = "pokemon-url-$index"
        )
    }
}