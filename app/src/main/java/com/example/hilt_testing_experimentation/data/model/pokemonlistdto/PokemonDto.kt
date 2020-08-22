package com.example.hilt_testing_experimentation.data.model.pokemonlistdto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonDto {
    @SerializedName("name")
    @Expose
    lateinit var name: String

    @SerializedName("url")
    @Expose
    lateinit var url: String
}