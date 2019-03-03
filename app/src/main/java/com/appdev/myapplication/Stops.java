package com.appdev.myapplication;

import com.google.gson.annotations.SerializedName;

public class Stops {

    private String name;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("long")
    private double longitude;

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

}
