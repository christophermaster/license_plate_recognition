package com.sandro.openalprsample;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.TransitionRes;
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
import com.sandro.openalprsample.crudDao.HistoricalAccessDao;
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



        List<AccessHistoryApi> accessHistoryApi = new ArrayList<AccessHistoryApi>();
        SQLiteDatabase db = helper.getWritableDatabase();
        accessHistoryApi = HistoricalAccessDao.listAccess(db);



        for(int i = 0; i < accessHistoryApi.size(); i++ ){
            accessHistoryApi.get(i).setCode( accessHistoryApi.get(i).getId());


            service .save("application/json; charset=UTF-8",accessHistoryApi.get(i)).enqueue(new Callback<ResponseBody>() {
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


    }


    public void comunity(View vista){

        // Instancia de la base de datos
        SQLiteDatabase db = null;
        Boolean success = false;


            try{
                db = helper.getWritableDatabase();

                if(db != null){

                    db.beginTransaction();
                    success = download(db);

                    if (success) {
                        db.setTransactionSuccessful();
                    }


                }

            }finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
            }








    }
    public Boolean download( SQLiteDatabase db){

        Long save = 0L;

        //verificico si json viene full

        if(comunity != null && db!= null){

            ComunityDao.createComunity(comunity,db);

            //verificar si la lista tiene registro
            if( comunity.getOwnerlist().size() != 0  ){

                //Guardar o actualizar propietario

                for(int i = 0 ; i < comunity.getOwnerlist().size(); i++){
                    System.out.println(i + comunity.getOwnerlist().get(i).getLastNameOwner());
                    //se veridifica si ya existe el propietario. Si no existe se crea y sino se modifica
                    if(!OwnerDao.exists(db,comunity.getOwnerlist().get(i).getIdentificationNumberOwner())){
                        save = OwnerDao.createOwner(comunity.getOwnerlist().get(i), comunity.getId(),db);

                    }else {
                        save =  Long.valueOf(OwnerDao.updateOwner(comunity.getOwnerlist().get(i), comunity.getId(),db));
                    }

                    if(save == -1){
                        return false;
                    }

                    //se verifica si tiene propiedad asociada
                    if (comunity.getOwnerlist().get(i).getListOwnership().size() != 0) {

                        // Se hace un ciclo para guardar las propiedades del  propietario
                        for (int j = 0; j < comunity.getOwnerlist().get(i).getListOwnership().size(); j++) {

                            //se veridifica si ya existe la propiedad. Si no existe se crea y sino se modifica
                            if (!PropertyDao.exists(db, comunity.getOwnerlist().get(i).getListOwnership().get(j).getOwnershipNumber())) {

                              save =  PropertyDao.createOwnership(comunity.getOwnerlist().get(i).getListOwnership().get(j),
                                        comunity.getOwnerlist().get(i).getId(), db);

                            } else {
                                save = Long.valueOf(PropertyDao.updateOwnership(comunity.getOwnerlist().get(i).getListOwnership().get(j),
                                        comunity.getOwnerlist().get(i).getId(), db));
                            }

                            if(save == -1){
                                return false;
                            }


                        }

                    }
                    //se verifica si tiene vehiculo asociada
                    if (comunity.getOwnerlist().get(i).getListVehicle().size() != 0) {
                        // Se hace un ciclo para guardar o modificar  los vehiculos del  propietario
                        for (int j = 0; j < comunity.getOwnerlist().get(i).getListVehicle().size(); j++) {

                            //se veridifica si ya existe el vehiculo. Si no existe se crea y sino se modifica
                            if (!VehicleDao.exists(db, comunity.getOwnerlist().get(i)
                                    .getListVehicle().get(j).getLecenseplateVehicle())) {

                                save =  VehicleDao.createVehicle(comunity.getOwnerlist()
                                                .get(i).getListVehicle().get(j),
                                        comunity.getOwnerlist().get(i).getId(), db);

                            } else {
                                save = Long.valueOf(VehicleDao.updateVehicle(comunity.getOwnerlist()
                                                .get(i).getListVehicle().get(j),
                                        comunity.getOwnerlist().get(i).getId(), db));
                            }

                            if(save == -1){
                                return false;
                            }

                        }

                    }

                }

            }

        }else{

            return false;
        }

        return true;

    }

    public void memoryInformation(View view){

        System.out.println("Total :" + (totalMemory()/1024)/1024);
        System.out.println("Libre :" + (freeMemory()/1024)/1024);
        System.out.println("Ocupado :" + (busyMemory()/1024)/1024);
        System.out.println("SDCard :" + megabytesAvailableSDCard());
        System.out.println("SDCard :" + megabytesAvailableInternalStorage(getBaseContext()));


    }

    public long totalMemory()
    {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long   total  = (statFs.getBlockCount() * statFs.getBlockSize());
        return total;
    }

    public long freeMemory()
    {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long   free   = (statFs.getAvailableBlocks() * statFs.getBlockSize());
        return free;
    }

    public long busyMemory()
    {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long   total  = (statFs.getBlockCount() * statFs.getBlockSize());
        long   free   = (statFs.getAvailableBlocks() * statFs.getBlockSize());
        long   busy   = total - free;
        return busy;
    }

    public static float megabytesAvailableSDCard() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long)stat.getBlockSize() * (long)stat.getAvailableBlocks()) / (1024.f * 1024.f);
    }

    public static float megabytesAvailableInternalStorage(Context ctx) {
        StatFs stat = new StatFs(ctx.getFilesDir().getAbsolutePath());
        return ((long)stat.getBlockSize() * (long)stat.getAvailableBlocks()) / (1024.f * 1024.f);
    }



}
