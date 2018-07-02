package com.sandro.openalprsample.entity;

public class HistoricalAccess {

    private Integer idHistorical;
    private Integer idOwner;
    private String date;
    private String hour;
    private String typeAccess;
    private String typeSecuriry;
    private String image;

    public HistoricalAccess() {
    }

    public HistoricalAccess(Integer idHistorical, Integer idOwner, String date, String hour, String typeAccess, String typeSecuriry, String image) {
        this.idHistorical = idHistorical;
        this.idOwner = idOwner;
        this.date = date;
        this.hour = hour;
        this.typeAccess = typeAccess;
        this.typeSecuriry = typeSecuriry;
        this.image = image;
    }


    public Integer getIdHistorical() {
        return idHistorical;
    }

    public void setIdHistorical(Integer idHistorical) {
        this.idHistorical = idHistorical;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
