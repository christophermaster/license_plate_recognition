package com.sandro.openalprsample.entity;

public class Comunity {

    private Integer com_id;
    private String com_name;
    private String com_type;

    public Comunity() {

    }


    public Comunity(Integer idComunity, String nameComunity, String typeComunity) {
        this.com_id = idComunity;
        this.com_name = nameComunity;
        this.com_type = typeComunity;
    }

    public Integer getIdComunity() {
        return com_id;
    }

    public void setIdComunity(Integer idComunity) {
        this.com_id = idComunity;
    }

    public String getNameComunity() {
        return com_name;
    }

    public void setNameComunity(String nameComunity) {
        this.com_name = nameComunity;
    }

    public String getTypeComunity() {
        return com_type;
    }

    public void setTypeComunity(String typeComunity) {
        this.com_type = typeComunity;
    }
}
