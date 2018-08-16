package com.sandro.openalprsample.apiRest.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessHistoryApi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("idOwner")
    @Expose
    private Integer idOwner;

    @SerializedName("com_id")
    @Expose
    private Integer com_id;

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("hour")
    @Expose
    private String hour;
    @SerializedName("typeaccess")
    @Expose
    private String typeaccess;
    @SerializedName("typesecurity")
    @Expose
    private String typesecurity;
    @SerializedName("photho")
    @Expose
    private byte[] photho;
    @SerializedName("ruta")
    @Expose
    private String ruta;

    public AccessHistoryApi() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTypeaccess() {
        return typeaccess;
    }

    public void setTypeaccess(String typeaccess) {
        this.typeaccess = typeaccess;
    }

    public String getTypesecurity() {
        return typesecurity;
    }

    public void setTypesecurity(String typesecurity) {
        this.typesecurity = typesecurity;
    }

    public byte[] getPhotho() {
        return photho;
    }

    public void setPhotho(byte[] photho) {
        this.photho = photho;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getOwn_id() {
        return idOwner;
    }

    public void setOwn_id(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }
}
