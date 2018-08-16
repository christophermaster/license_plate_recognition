package com.sandro.openalprsample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sandro.openalprsample.apiRest.models.AccessHistoryApi;
import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.apiRest.serviceProvider.ServiceProvider;
import com.sandro.openalprsample.apiRest.services.ApiService;
import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.ComunityDao;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.crudDao.PropertyDao;
import com.sandro.openalprsample.crudDao.VehicleDao;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListApiRest extends AppCompatActivity {


    private ApiService service;
    private BBDD_Helper helper ;

    private Button accessButton;

    private List<ComunityApi> list = new ArrayList<>();
    private ComunityApi comunity = new ComunityApi();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_api_rest);



        service = ServiceProvider.getService();
        helper = new BBDD_Helper(this);
        accessButton = (Button) findViewById(R.id.bAccess);


        this.getComunity();

        accessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });



    }


    /*Get */
    public ComunityApi getComunity() {

        int id = 1;

        service.getComunidad(id).enqueue(new Callback<ComunityApi>() {

            @Override
            public void onResponse(Call<ComunityApi> call, Response<ComunityApi> response) {

                if (response.isSuccessful()){
                    comunity = response.body();
                }else {

                    System.out.println("Algo Salio Mal");
                }
            }

            @Override
            public void onFailure(Call<ComunityApi> call, Throwable t) {
                System.out.println("No se Realizo la Conexión");
            }
        });

        return comunity;
    }


    public void create(){


        AccessHistoryApi accessHistoryApi = new AccessHistoryApi();



        accessHistoryApi.setDate("fecha");
        accessHistoryApi.setHour("hora");
        accessHistoryApi.setPhotho(null);
        accessHistoryApi.setRuta(null);
        accessHistoryApi.setTypeaccess("e");
        accessHistoryApi.setTypesecurity("s");
        accessHistoryApi.setOwn_id(1);



        service .save("application/json; charset=UTF-8",accessHistoryApi).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    System.out.println(response.message());

                }else {
                    System.out.println(response.message());
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Mal");
            }
        });



    }

    public void comunity(View vista){



        System.out.println("Id Comunidad : " + comunity.getId());
        System.out.println("Name Comunidad : " + comunity.getNameComunity());
        System.out.println("Tipo Comunidad : " + comunity.getTypeComunity());

        SQLiteDatabase db = helper.getWritableDatabase();


        Long save = 0L;

        if(comunity != null){

            save = ComunityDao.createComunity(comunity, db);

            System.out.println("Propietarios :" + comunity.getOwnerlist().size() );

            for(int i = 0; i < comunity.getOwnerlist().size(); i ++ ){

                if(!OwnerDao.exists(db,comunity.getOwnerlist().get(i).getIdentificationNumberOwner())){

                    save = OwnerDao.createOwner(comunity.getOwnerlist().get(i), comunity.getId(),db);

                }


                if(save != 0 ){


                    if(comunity.getOwnerlist().get(i).getListOwnership() != null){

                        for(int j = 0; j < comunity.getOwnerlist().get(i).getListOwnership().size(); j ++ ){

                            if(!PropertyDao.exists(db, comunity.getOwnerlist().get(i).getListOwnership().get(j).getOwnershipNumber())){

                                save = PropertyDao.createOwnership(comunity.getOwnerlist().get(i).getListOwnership().get(j),
                                        comunity.getOwnerlist().get(i).getId(), db);

                            }



                        }

                    }

                    if(comunity.getOwnerlist().get(i).getListVehicle() != null){

                        for(int j = 0; j < comunity.getOwnerlist().get(i).getListOwnership().size(); j++){

                            if(!VehicleDao.exists(db, comunity.getOwnerlist().get(i)
                                    .getListVehicle().get(j).getLecenseplateVehicle() )){

                                save = VehicleDao.createVehicle(comunity.getOwnerlist()
                                                .get(i).getListVehicle().get(j),
                                                comunity.getOwnerlist().get(i).getId(), db);

                            }


                        }

                    }

                }else{

                    System.out.println("Entidad Vacia");
                }

            }

        }

    }




}
