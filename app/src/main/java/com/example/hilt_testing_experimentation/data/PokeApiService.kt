package com.example.hilt_testing_experimentation.data

import com.example.hilt_testing_experimentation.data.model.detailedpokemon.DetailedPokemonDto
import com.example.hilt_testing_experimentation.data.model.pokemonlist.PokemonListDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?limit=20")
    fun getPokemon(): Single<PokemonListDto>

    @GET("pokemon/{name}")
    fun getDetailedPokemon(@Path("name") id: String): Single<DetailedPokemonDto>
}