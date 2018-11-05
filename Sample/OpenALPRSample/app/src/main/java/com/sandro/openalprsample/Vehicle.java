package com.sandro.openalprsample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sandro.openalprsample.apiRest.models.OwnerApi;
import com.sandro.openalprsample.apiRest.models.VehicleApi;
import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.crudDao.VehicleDao;

import java.util.ArrayList;

public class Vehicle extends AppCompatActivity {

    //EDIT TEXT
    private EditText make,
                    idVehicle,
                    pattern,
                    color,
                    year,
                    plateNumber;

    //SPINNER
    private Spinner spinnerNameOwner,spinnerlistVehicle ;
    private ArrayAdapter<CharSequence> adaptadorOwner;

    //Lista utilizada para mostrar los eleemntos en el spinner
    private ArrayList<String>  listOwnerSpinner ,
                               listVehicleSpinner ;

    //lista utlizada para almecenar los modelos
    private ArrayList<OwnerApi> listOwnerEntity;
    private ArrayList<VehicleApi> listVehicleEntity;


    private Integer idOwner = 0 ;


    private BBDD_Helper helper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        //Se inicializa los componentes
        idVehicle = (EditText)findViewById(R.id.idVehicle);
        make = (EditText)findViewById(R.id.marca);
        pattern = (EditText)findViewById(R.id.modelo);
        color = (EditText)findViewById(R.id.color);
        year = (EditText)findViewById(R.id.ano);
        plateNumber = (EditText)findViewById(R.id.placa);

        spinnerNameOwner= (Spinner)findViewById(R.id.nameOwner);
        spinnerlistVehicle = (Spinner)findViewById(R.id.listVehicle);

        //Se instacia el
        helper = new BBDD_Helper(this);


        //Se llena La Lista que se utlizara en el sppiner
        listOwner();


        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
        spinnerNameOwner.setAdapter(adaptadorOwner);
        spinnerNameOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)

                    idOwner = listOwnerEntity.get(position-1).getId();

                else{
                    idOwner = 0;
                    Toast.makeText(getApplicationContext(),"Elegir Propietario", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Se llena La Lista que se utlizara en el sppiner

        listarVehicle();
        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listVehicleSpinner);
        spinnerlistVehicle.setAdapter(adaptadorOwner);
        spinnerlistVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    /**NEV CREATE
     *
     * @param view
     * **/
    public void  create(View view){

        //Se instancia la bd
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia el modelo
        VehicleApi model = new VehicleApi();

        //Se almacena los datos  al modelo
        model.setMakeVehicle(make.getText().toString());
        model.setColourVehicle( color.getText().toString());
        model.setLecenseplateVehicle(plateNumber.getText().toString());
        model.setLongVehicle(year.getText().toString());
        model.setModelVehicle(pattern.getText().toString());

        //Se crea el nuevo registro
        Long newRowId = VehicleDao.createVehicle(model,idOwner,db );

        //Se verefica si el registro se guardo o no correctamente
        if(newRowId == -1){

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();

        }else{

            listarVehicle();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listVehicleSpinner);
            spinnerlistVehicle.setAdapter(adaptadorOwner);
            Toast.makeText(getApplicationContext(),"Se guardó el registro ", Toast.LENGTH_LONG).show();
            listOwner();

        }

    }

    /**List all owner in spinner
     *
     * */
    private void listOwner() {

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia la lista que se utulizara para mostrar lo elementos en el Spinner
        listOwnerSpinner = new ArrayList<>();

        //Se instancia la lista que tendra los propietarios
        listOwnerEntity = new ArrayList<>();

        //Se almacena ls lista de propietarios
        listOwnerEntity =  OwnerDao.listOwner(db);

        //Agregamos el primer elemento que se mostrara en el Spinner
        listOwnerSpinner.add("Lista de propietarios");

        //Se verifica si existe propietarios o no
        if(listOwnerEntity!=null)
            //Se llena el Spinner
            for (int i= 0; i<listOwnerEntity.size();i++){
                listOwnerSpinner.add(listOwnerEntity.get(i).getNameOwner() + "-"
                        + listOwnerEntity.get(i).getTypeIdentificationNumberOwner());

            }




    }

    /**List all vehicle in spinner
     *
     * */
    private void listarVehicle(){

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia la lista que se utulizara para mostrar lo elementos en el Spinner
        listVehicleSpinner = new ArrayList<>();

        //Se instancia la lista que tendra los vehiculos
        listVehicleEntity = new ArrayList<>();

        //Se almacena ls lista de vehiculo
        listVehicleEntity =  VehicleDao.listVehicle(db);

        //Agregamos el primer elemento que se mostrara en el Spinner
        listVehicleSpinner.add("Lista de Vehiculos");

        //Se verifica si existe vehiculos o no
        if(listVehicleEntity!=null)
            //Se llena el Spinner
            for (int i= 0; i<listVehicleEntity.size();i++){

                listVehicleSpinner.add(listVehicleEntity.get(i).getId() + "-" +
                        listVehicleEntity.get(i).getLecenseplateVehicle() );

            }

    }



}
