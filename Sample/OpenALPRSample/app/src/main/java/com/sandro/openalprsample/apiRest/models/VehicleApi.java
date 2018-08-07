package com.sandro.openalprsample.apiRest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleApi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("makeVehicle")
    @Expose
    private String makeVehicle;
    @SerializedName("modelVehicle")
    @Expose
    private String modelVehicle;
    @SerializedName("colourVehicle")
    @Expose
    private String colourVehicle;
    @SerializedName("lecenseplateVehicle")
    @Expose
    private String lecenseplateVehicle;
    @SerializedName("longVehicle")
    @Expose
    private Integer longVehicle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMakeVehicle() {
        return makeVehicle;
    }

    public void setMakeVehicle(String makeVehicle) {
        this.makeVehicle = makeVehicle;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

    public void setModelVehicle(String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    public String getColourVehicle() {
        return colourVehicle;
    }

    public void setColourVehicle(String colourVehicle) {
        this.colourVehicle = colourVehicle;
    }

    public String getLecenseplateVehicle() {
        return lecenseplateVehicle;
    }

    public void setLecenseplateVehicle(String lecenseplateVehicle) {
        this.lecenseplateVehicle = lecenseplateVehicle;
    }

    public Integer getLongVehicle() {
        return longVehicle;
    }

    public void setLongVehicle(Integer longVehicle) {
        this.longVehicle = longVehicle;
    }

}
