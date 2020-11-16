package com.example.feature_pokemonlist.data.model.detailedpokemondto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GameIndexDto : Serializable {
    @SerializedName("game_index")
    @Expose
    var gameIndex: Long? = null

    @SerializedName("version")
    @Expose
    var version: VersionDto? = null
}