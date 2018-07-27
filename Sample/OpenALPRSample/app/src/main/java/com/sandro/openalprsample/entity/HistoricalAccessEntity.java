package com.sandro.openalprsample.entity;

import java.sql.Blob;

public class HistoricalAccessEntity {

    private Integer idHistorical;
    private Integer own_id;
    private String date;
    private String hour;
    private String typeAccess;
    private String typeSecuriry;
    private byte[] image;

    public Integer getOwn_id() {
        return own_id;
    }

    public void setOwn_id(Integer own_id) {
        this.own_id = own_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public HistoricalAccessEntity() {
    }



    public Integer getIdHistorical() {
        return idHistorical;
    }

    public void setIdHistorical(Integer idHistorical) {
        this.idHistorical = idHistorical;
    }

    public Integer getIdOwner() {
        return own_id;
    }

    public void setIdOwner(Integer idOwner) {
        this.own_id = idOwner;
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

    public String getTypeAccess() {
        return typeAccess;
    }

    public void setTypeAccess(String typeAccess) {
        this.typeAccess = typeAccess;
    }

    public String getTypeSecuriry() {
        return typeSecuriry;
    }

    public void setTypeSecuriry(String typeSecuriry) {
        this.typeSecuriry = typeSecuriry;
    }


}
