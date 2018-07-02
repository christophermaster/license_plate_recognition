package com.sandro.openalprsample.entity;

public class OwnerEntity {

    private Integer idOwner;
    private Integer idcommunity;
    private String nameOwner;
    private String surnameOwner;
    private String numberIdentity;
    private String typeIdentity;
    private String typeOwner;

    public OwnerEntity() {
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getIdcommunity() {
        return idcommunity;
    }

    public void setIdcommunity(Integer idcommunity) {
        this.idcommunity = idcommunity;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getSurnameOwner() {
        return surnameOwner;
    }

    public void setSurnameOwner(String surnameOwner) {
        this.surnameOwner = surnameOwner;
    }

    public String getNumberIdentity() {
        return numberIdentity;
    }

    public void setNumberIdentity(String numberIdentity) {
        this.numberIdentity = numberIdentity;
    }

    public String getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(String typeIdentity) {
        this.typeIdentity = typeIdentity;
    }

    public String getTypeOwner() {
        return typeOwner;
    }

    public void setTypeOwner(String typeOwner) {
        this.typeOwner = typeOwner;
    }
}
