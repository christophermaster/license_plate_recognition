package com.sandro.openalprsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.apiRest.models.VehicleApi;
import com.sandro.openalprsample.apiRest.models.VehicleService;
import com.sandro.openalprsample.apiRest.serviceProvider.ServiceProvider;
import com.sandro.openalprsample.apiRest.services.ComunityService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListApiRest extends AppCompatActivity {


    private ComunityService service;

    private List<ComunityApi> list = new ArrayList<>();
    private ComunityApi comunity = new ComunityApi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_api_rest);

        service = ServiceProvider.getService();

        getList();



    }

    public List<ComunityApi> getList() {

        Integer id = 1;

        service.getComunidad(id).enqueue(new Callback<ComunityApi>() {

            @Override
            public void onResponse(Call<ComunityApi> call, Response<ComunityApi> response) {
                if (response.isSuccessful()){

                    comunity = response.body();

                }
            }

            @Override
            public void onFailure(Call<ComunityApi> call, Throwable t) {

                System.out.print("Mal");

            }
        });
        return list;
    }





    public void comunity(View vista){

        System.out.println("Comunidad : " + comunity.getNameComunity());
        System.out.println("Propietario : " + comunity.getOwnerlist().size());

    }


}
