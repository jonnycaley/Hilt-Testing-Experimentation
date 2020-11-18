package com.example.core_navigation

import com.example.core.domain.detailedpokemon.DetailedPokemon

interface NavigationProvider {
    fun toPokemonDetail(detailedPokemon: DetailedPokemon)
}