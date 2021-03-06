package com.example.core.data.model.detailedpokemondto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AbilityDto : Serializable {
    @SerializedName("ability")
    @Expose
    var ability: AbilityDto_? = null

    @SerializedName("is_hidden")
    @Expose
    var isHidden: Boolean? = null

    @SerializedName("slot")
    @Expose
    var slot: Long? = null
}