package com.sandro.openalprsample.entity;

public class VehicleEntity {

    private Integer idVehicle;
    private Integer idOwner;
    private String  make;
    private String pattern;
    private String color;
    private Integer year;
    private String plateNumber;

    public VehicleEntity() {
    }

    public VehicleEntity(Integer idVehicle, Integer idOwner, String make, String pattern, String color, Integer year, String plateNumber) {
        this.idVehicle = idVehicle;
        this.idOwner = idOwner;
        this.make = make;
        this.pattern = pattern;
        this.color = color;
        this.year = year;
        this.plateNumber = plateNumber;
    }

    public Integer getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Integer idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
