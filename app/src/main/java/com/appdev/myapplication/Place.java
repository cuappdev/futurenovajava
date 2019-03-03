package com.appdev.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class Place {

    private String type;
    private String name;

    //Optional Fields

    @SerializedName("lat")
    private Double latitude;

    @SerializedName("long")
    private Double longitude;

    private String detail;

    private String placeID;

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }
}
