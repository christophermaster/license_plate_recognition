package com.sandro.openalprsample.entity;

public class OwnerEntity {

    private Integer own_id;
    private Integer com_id;
    private String own_name;
    private String own_lasname;
    private String own_identification_number;
    private String own_type_identification;


    public OwnerEntity() {
    }

    public Integer getOwn_id() {
        return own_id;
    }

    public void setOwn_id(Integer own_id) {
        this.own_id = own_id;
    }

    public Integer getCom_id() {
        return com_id;
    }

    public void setCom_id(Integer com_id) {
        this.com_id = com_id;
    }

    public String getOwn_name() {
        return own_name;
    }

    public void setOwn_name(String own_name) {
        this.own_name = own_name;
    }

    public String getOwn_lasname() {
        return own_lasname;
    }

    public void setOwn_lasname(String own_lasname) {
        this.own_lasname = own_lasname;
    }

    public String getOwn_identification_number() {
        return own_identification_number;
    }

    public void setOwn_identification_number(String own_identification_number) {
        this.own_identification_number = own_identification_number;
    }

    public String getOwn_type_identification() {
        return own_type_identification;
    }

    public void setOwn_type_identification(String own_type_identification) {
        this.own_type_identification = own_type_identification;
    }
}
