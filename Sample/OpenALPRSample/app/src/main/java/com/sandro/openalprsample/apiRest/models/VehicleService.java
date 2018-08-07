package com.sandro.openalprsample.apiRest.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VehicleService {

    @GET("RacsWeb/vehiculo/listado")
    Call<List<VehicleApi>> getVehicleList();
}
