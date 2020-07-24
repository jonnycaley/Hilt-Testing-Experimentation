package com.example.hilt_testing_experimentation.usecase

import com.example.hilt_testing_experimentation.data.PokeApiService
import com.example.hilt_testing_experimentation.data.model.detailedpokemon.DetailedPokemonDto
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val pokeService: PokeApiService
) {
    operator fun invoke(): Observable<DetailedPokemonDto> {
        return pokeService.getPokemon()
            .flattenAsObservable { it.results }
            .flatMap { pokeService.getDetailedPokemon(it.name).toObservable() }
    }
}