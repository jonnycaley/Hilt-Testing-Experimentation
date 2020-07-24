package com.example.hilt_testing_experimentation.data.model.detailedpokemon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailedPokemonDto {
    @SerializedName("abilities")
    @Expose
    var abilities: List<AbilityDto>? = null

    @SerializedName("base_experience")
    @Expose
    var baseExperience: Int? = null

    @SerializedName("forms")
    @Expose
    var forms: List<FormDto>? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("is_default")
    @Expose
    var isDefault: Boolean? = null

    @SerializedName("location_area_encounters")
    @Expose
    var locationAreaEncounters: String? = null

    @SerializedName("moves")
    @Expose
    var moves: List<MoveDto>? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("order")
    @Expose
    var order: Int? = null

    @SerializedName("species")
    @Expose
    var species: Species? = null

    @SerializedName("sprites")
    @Expose
    var sprites: Sprites? = null

    @SerializedName("stats")
    @Expose
    var stats: List<Stat>? = null

    @SerializedName("types")
    @Expose
    var types: List<Type>? = null

    @SerializedName("weight")
    @Expose
    var weight: Int? = null
}