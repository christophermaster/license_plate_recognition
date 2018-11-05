package com.sandro.openalprsample.apiRest.services;

import com.sandro.openalprsample.apiRest.models.AccessHistoryApi;
import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.apiRest.models.DeviceApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

    //Comunity
    @GET("RacsWeb/comunidad/{id}")
    Call<ComunityApi> getComunidad(@Path("id") int id);


    //History
    @POST("RacsWeb/acceso/guardar")
    Call<ResponseBody> save(@Header("Content-Type") String authkey , @Body AccessHistoryApi access);

    //Device
    @POST("RacsWeb/dispositivo/guardar")
    Call<ResponseBody> save(@Header("Content-Type") String authkey , @Body DeviceApi device);
}
