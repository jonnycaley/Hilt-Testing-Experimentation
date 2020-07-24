
package com.example.hilt_testing_experimentation.data.model.detailedpokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Species {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("url")
    @Expose
    public String url;

}
