
package com.example.core.data.model.detailedpokemondto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpeciesDto {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("url")
    @Expose
    public String url;

}
