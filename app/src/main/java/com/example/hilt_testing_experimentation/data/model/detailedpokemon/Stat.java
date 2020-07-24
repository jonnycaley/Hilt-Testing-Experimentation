
package com.example.hilt_testing_experimentation.data.model.detailedpokemon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("base_stat")
    @Expose
    public Integer baseStat;
    @SerializedName("effort")
    @Expose
    public Integer effort;
    @SerializedName("stat")
    @Expose
    public Stat_ stat;

}
