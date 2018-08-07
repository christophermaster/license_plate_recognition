package com.sandro.openalprsample.apiRest.services;

import com.sandro.openalprsample.Comunity;
import com.sandro.openalprsample.apiRest.models.ComunityApi;

import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComunityService {


    @POST("crear")
    Call<ResponseBody> createComunity(@Body Comunity comunity);


    @GET("comunidad/{id}")
    Call<ComunityApi> getComunidad(@Path("id") Integer id);

    @GET("comunida")
    Call<List<ComunityApi>> listComunity(@Query("name") String name);


}
