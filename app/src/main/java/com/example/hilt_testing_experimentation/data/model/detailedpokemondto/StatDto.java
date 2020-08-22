
package com.example.hilt_testing_experimentation.data.model.detailedpokemondto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatDto {

    @SerializedName("base_stat")
    @Expose
    public Integer baseStat;
    @SerializedName("effort")
    @Expose
    public Integer effort;
    @SerializedName("stat")
    @Expose
    public StatDto_ stat;

}
