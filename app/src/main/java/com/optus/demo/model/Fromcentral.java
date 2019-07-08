package com.optus.demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fromcentral implements Serializable {
    @SerializedName("car")
    private String car;
    @SerializedName("train")
    private String train;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }
}

