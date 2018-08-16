package com.sandro.openalprsample.apiRest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DeviceApi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serialDevice")
    @Expose
    private String serialDevice;
    @SerializedName("ipFTP")
    @Expose
    private String ipFTP;
    @SerializedName("portFTP")
    @Expose
    private String portFTP;
    @SerializedName("userFTP")
    @Expose
    private String userFTP;
    @SerializedName("passFTP")
    @Expose
    private String passFTP;
    @SerializedName("stateDevice")
    @Expose
    private Boolean stateDevice;
    @SerializedName("versionAPP")
    @Expose
    private Object versionAPP;
    @SerializedName("total")
    @Expose
    private Object total;
    @SerializedName("busy")
    @Expose
    private Object busy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialDevice() {
        return serialDevice;
    }

    public void setSerialDevice(String serialDevice) {
        this.serialDevice = serialDevice;
    }

    public String getIpFTP() {
        return ipFTP;
    }

    public void setIpFTP(String ipFTP) {
        this.ipFTP = ipFTP;
    }

    public String getPortFTP() {
        return portFTP;
    }

    public void setPortFTP(String portFTP) {
        this.portFTP = portFTP;
    }

    public String getUserFTP() {
        return userFTP;
    }

    public void setUserFTP(String userFTP) {
        this.userFTP = userFTP;
    }

    public String getPassFTP() {
        return passFTP;
    }

    public void setPassFTP(String passFTP) {
        this.passFTP = passFTP;
    }

    public Boolean getStateDevice() {
        return stateDevice;
    }

    public void setStateDevice(Boolean stateDevice) {
        this.stateDevice = stateDevice;
    }

    public Object getVersionAPP() {
        return versionAPP;
    }

    public void setVersionAPP(Object versionAPP) {
        this.versionAPP = versionAPP;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getBusy() {
        return busy;
    }

    public void setBusy(Object busy) {
        this.busy = busy;
    }


}
