package com.example.feature_pokemonlist.domain.pokemonlist

object PokemonBuilder {

    fun buildPokemon(index: Int): Pokemon {
        return Pokemon(
            name = "pokemon-name-$index",
            url = "pokemon-url-$index"
        )
    }
}