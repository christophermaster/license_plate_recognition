package com.sandro.openalprsample.apiRest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComunityApi {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameComunity")
    @Expose
    private String nameComunity;
    @SerializedName("typeComunity")
    @Expose
    private String typeComunity;
    @SerializedName("ownerlist")
    @Expose
    private List<OwnerApi> ownerlist = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<OwnerApi> getOwnerlist() {
        return ownerlist;
    }

    public void setOwnerlist(List<OwnerApi> ownerlist) {
        this.ownerlist = ownerlist;
    }


}
