package com.sandro.openalprsample.entity;

public class Comunity {

    private Integer idComunity;
    private String nameComunity;
    private String typeComunity;

    public Comunity() {

    }


    public Comunity(Integer idComunity, String nameComunity, String typeComunity) {
        this.idComunity = idComunity;
        this.nameComunity = nameComunity;
        this.typeComunity = typeComunity;
    }

    public Integer getIdComunity() {
        return idComunity;
    }

    public void setIdComunity(Integer idComunity) {
        this.idComunity = idComunity;
    }

    public String getNameComunity() {
        return nameComunity;
    }

    public void setNameComunity(String nameComunity) {
        this.nameComunity = nameComunity;
    }

    public String getTypeComunity() {
        return typeComunity;
    }

    public void setTypeComunity(String typeComunity) {
        this.typeComunity = typeComunity;
    }
}
