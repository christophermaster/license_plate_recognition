package com.sandro.openalprsample.entity;

public class OwnerShipEntity {

    private Integer osh_id;
    private Integer own_id;
    private Double osh_area;
    private Integer osh_inhabited;
    private String osh_number;
    private String osh_type;

    public OwnerShipEntity() {


    }

    public Integer getOsh_id() {
        return osh_id;
    }

    public void setOsh_id(Integer osh_id) {
        this.osh_id = osh_id;
    }

    public Integer getOwn_id() {
        return own_id;
    }

    public void setOwn_id(Integer own_id) {
        this.own_id = own_id;
    }

    public Double getOsh_area() {
        return osh_area;
    }

    public void setOsh_area(Double osh_area) {
        this.osh_area = osh_area;
    }

    public Integer getOsh_inhabited() {
        return osh_inhabited;
    }

    public void setOsh_inhabited(Integer osh_inhabited) {
        this.osh_inhabited = osh_inhabited;
    }

    public String getOsh_number() {
        return osh_number;
    }

    public void setOsh_number(String osh_number) {
        this.osh_number = osh_number;
    }

    public String getOsh_type() {
        return osh_type;
    }

    public void setOsh_type(String osh_type) {
        this.osh_type = osh_type;
    }
}
