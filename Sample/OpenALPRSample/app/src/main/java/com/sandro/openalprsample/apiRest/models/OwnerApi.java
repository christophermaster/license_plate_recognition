package com.sandro.openalprsample.apiRest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OwnerApi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameOwner")
    @Expose
    private String nameOwner;
    @SerializedName("lastNameOwner")
    @Expose
    private String lastNameOwner;
    @SerializedName("identificationNumberOwner")
    @Expose
    private String identificationNumberOwner;
    @SerializedName("typeIdentificationNumberOwner")
    @Expose
    private String typeIdentificationNumberOwner;
    @SerializedName("listVehicle")
    @Expose
    private List<VehicleApi> listVehicle = null;
    @SerializedName("listOwnership")
    @Expose
    private List<OwnershipApi> listOwnership = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getLastNameOwner() {
        return lastNameOwner;
    }

    public void setLastNameOwner(String lastNameOwner) {
        this.lastNameOwner = lastNameOwner;
    }

    public String getIdentificationNumberOwner() {
        return identificationNumberOwner;
    }

    public void setIdentificationNumberOwner(String identificationNumberOwner) {
        this.identificationNumberOwner = identificationNumberOwner;
    }

    public String getTypeIdentificationNumberOwner() {
        return typeIdentificationNumberOwner;
    }

    public void setTypeIdentificationNumberOwner(String typeIdentificationNumberOwner) {
        this.typeIdentificationNumberOwner = typeIdentificationNumberOwner;
    }

    public List<VehicleApi> getListVehicle() {
        return listVehicle;
    }

    public void setListVehicle(List<VehicleApi> listVehicle) {
        this.listVehicle = listVehicle;
    }

    public List<OwnershipApi> getListOwnership() {
        return listOwnership;
    }

    public void setListOwnership(List<OwnershipApi> listOwnership) {
        this.listOwnership = listOwnership;
    }


}
