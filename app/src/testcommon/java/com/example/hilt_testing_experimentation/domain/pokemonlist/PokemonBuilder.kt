package com.example.hilt_testing_experimentation.domain.pokemonlist

object PokemonBuilder {

    fun buildPokemon(index: Int): Pokemon {
        return Pokemon(
            name = "pokemon-name-$index",
            url = "pokemon-url-$index"
        )
    }
}