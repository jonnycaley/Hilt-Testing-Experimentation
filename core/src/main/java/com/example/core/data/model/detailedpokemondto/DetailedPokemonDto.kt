package com.example.core.data.model.detailedpokemondto

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
    var speciesDto: SpeciesDto? = null

    @SerializedName("sprites")
    @Expose
    var spritesDto: SpritesDto? = null

    @SerializedName("stats")
    @Expose
    var statDtos: List<StatDto>? = null

    @SerializedName("types")
    @Expose
    var typeDtos: List<TypeDto>? = null

    @SerializedName("weight")
    @Expose
    var weight: Int? = null
}