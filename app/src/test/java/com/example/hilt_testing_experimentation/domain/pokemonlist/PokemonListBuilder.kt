package com.example.hilt_testing_experimentation.domain.pokemonlist

object PokemonListBuilder {
    fun build(): PokemonList {
        return PokemonList(
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            results = listOf(
                PokemonBuilder.pikachu
            )
        )
    }
}