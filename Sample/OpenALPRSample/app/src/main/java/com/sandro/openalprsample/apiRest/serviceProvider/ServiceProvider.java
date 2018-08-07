package com.sandro.openalprsample.apiRest.serviceProvider;

import com.sandro.openalprsample.apiRest.services.ComunityService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static ComunityService service;

    public static ComunityService getService(){

        if(service == null){

            service = new Retrofit.Builder()
                    .baseUrl("http://192.168.99.1:8090/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ComunityService.class);

        }

        return service;

    }

}
