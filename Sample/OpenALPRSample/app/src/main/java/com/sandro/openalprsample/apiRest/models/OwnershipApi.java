package com.sandro.openalprsample.apiRest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwnershipApi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ownershipNumber")
    @Expose
    private String ownershipNumber;
    @SerializedName("typeOwnership")
    @Expose
    private String typeOwnership;
    @SerializedName("inhabited")
    @Expose
    private Boolean inhabited;
    @SerializedName("area")
    @Expose
    private Double area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnershipNumber() {
        return ownershipNumber;
    }

    public void setOwnershipNumber(String ownershipNumber) {
        this.ownershipNumber = ownershipNumber;
    }

    public String getTypeOwnership() {
        return typeOwnership;
    }

    public void setTypeOwnership(String typeOwnership) {
        this.typeOwnership = typeOwnership;
    }

    public Boolean getInhabited() {
        return inhabited;
    }

    public void setInhabited(Boolean inhabited) {
        this.inhabited = inhabited;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }



}
