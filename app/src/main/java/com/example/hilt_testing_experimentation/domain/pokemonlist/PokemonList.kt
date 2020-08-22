package com.example.hilt_testing_experimentation.domain.pokemonlist

data class PokemonList(
    var next: String?,
    var results: List<Pokemon>
)