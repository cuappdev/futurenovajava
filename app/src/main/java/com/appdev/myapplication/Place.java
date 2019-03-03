package com.appdev.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class Place {

    private String type;
    private String name;

    @SerializedName("lat")
    private Optional<Double> latitude;

    @SerializedName("long")
    private Optional<Double> longitude;

    private Optional<String> detail;

    private Optional<String> placeID;

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }
}
