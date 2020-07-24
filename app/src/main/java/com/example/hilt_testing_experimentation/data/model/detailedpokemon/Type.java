
package com.example.hilt_testing_experimentation.data.model.detailedpokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("slot")
    @Expose
    public Integer slot;
    @SerializedName("type")
    @Expose
    public Type_ type;

}
