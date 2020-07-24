package com.example.hilt_testing_experimentation.data.model.pokemonlist

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class PokemonListDto {
    @SerializedName("count")
    @Expose
    var count: Long? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("previous")
    @Expose
    var previous: String? = null

    @SerializedName("results")
    @Expose
    var results: List<PokemonDto> = emptyList()
}

