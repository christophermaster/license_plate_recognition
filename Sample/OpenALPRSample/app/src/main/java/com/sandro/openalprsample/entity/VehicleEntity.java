package com.sandro.openalprsample.entity;

public class VehicleEntity {

    private Integer veh_id;
    private Integer own_id;
    private Integer veh_year;
    private String  veh_colour;
    private String veh_licenceplate;
    private String veh_make;
    private String veh_model;

    public VehicleEntity() {
    }

    public Integer getVeh_id() {
        return veh_id;
    }

    public void setVeh_id(Integer veh_id) {
        this.veh_id = veh_id;
    }

    public Integer getOwn_id() {
        return own_id;
    }

    public void setOwn_id(Integer own_id) {
        this.own_id = own_id;
    }

    public Integer getVeh_year() {
        return veh_year;
    }

    public void setVeh_year(Integer veh_year) {
        this.veh_year = veh_year;
    }

    public String getVeh_colour() {
        return veh_colour;
    }

    public void setVeh_colour(String veh_colour) {
        this.veh_colour = veh_colour;
    }

    public String getVeh_licenceplate() {
        return veh_licenceplate;
    }

    public void setVeh_licenceplate(String veh_licenceplate) {
        this.veh_licenceplate = veh_licenceplate;
    }

    public String getVeh_make() {
        return veh_make;
    }

    public void setVeh_make(String veh_make) {
        this.veh_make = veh_make;
    }

    public String getVeh_model() {
        return veh_model;
    }

    public void setVeh_model(String veh_model) {
        this.veh_model = veh_model;
    }
}
