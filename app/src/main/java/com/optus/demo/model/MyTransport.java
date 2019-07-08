package com.optus.demo.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyTransport implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private Location location;
    @SerializedName("id")
    private String id;
    @SerializedName("fromcentral")
    private Fromcentral fromcentral;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fromcentral getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(Fromcentral fromcentral) {
        this.fromcentral = fromcentral;
    }
}