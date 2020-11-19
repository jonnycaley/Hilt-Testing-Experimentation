
package com.example.core.data.model.detailedpokemondto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeDto {

    @SerializedName("slot")
    @Expose
    public Integer slot;
    @SerializedName("type")
    @Expose
    public TypeDto_ type;

}
