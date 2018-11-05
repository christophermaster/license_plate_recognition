package com.sandro.openalprsample.apiRest.serviceProvider;

import com.sandro.openalprsample.apiRest.services.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static ApiService service;

    public static ApiService getService(){


        if(service == null){

            // Se crea la instancia Retrofit

            service = new Retrofit.Builder()
                    .baseUrl("http://10.0.0.11:8090/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService.class);

        }

        return service;

    }

}
