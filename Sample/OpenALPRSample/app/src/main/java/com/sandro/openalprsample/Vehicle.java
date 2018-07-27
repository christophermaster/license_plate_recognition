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

import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.crudDao.PropertyDao;
import com.sandro.openalprsample.crudDao.VehicleDao;
import com.sandro.openalprsample.entity.OwnerEntity;
import com.sandro.openalprsample.entity.VehicleEntity;

import java.util.ArrayList;

public class Vehicle extends AppCompatActivity {

    private EditText make,
                    idVehicle,
                    pattern,
                    color,
                    year,
                    plateNumber;




    private BBDD_Helper helper ;
    private Spinner spinnerNameOwner,spinnerlistVehicle ;

    private ArrayList<String>  listOwnerSpinner ;
    private ArrayList<String>  listVehicleSpinner ;

    private ArrayAdapter<CharSequence> adaptadorOwner;
    private ArrayList<OwnerEntity> listOwnerEntity;
    private ArrayList<VehicleEntity> listVehicleEntity;
    private Integer idOwner = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        idVehicle = (EditText)findViewById(R.id.idVehicle);
        make = (EditText)findViewById(R.id.marca);
        pattern = (EditText)findViewById(R.id.modelo);
        color = (EditText)findViewById(R.id.color);
        year = (EditText)findViewById(R.id.ano);
        plateNumber = (EditText)findViewById(R.id.placa);

        spinnerNameOwner= (Spinner)findViewById(R.id.nameOwner);
        spinnerlistVehicle = (Spinner)findViewById(R.id.listVehicle);

        helper = new BBDD_Helper(this);


        listOwner();
        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
        spinnerNameOwner.setAdapter(adaptadorOwner);
        spinnerNameOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)

                    idOwner = listOwnerEntity.get(position-1).getOwn_id();

                else{
                    idOwner = 0;
                    Toast.makeText(getApplicationContext(),"Elegir Propietario", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listarVehicle();
        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listVehicleSpinner);
        spinnerlistVehicle.setAdapter(adaptadorOwner);
        spinnerlistVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)
                    System.out.print("aqui");
                else{

                    System.out.print("aqui si");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    public void  create(View view){

        SQLiteDatabase db = helper.getWritableDatabase();


        Long newRowId = VehicleDao.createVehicle(idOwner, make.getText().toString(), pattern.getText().toString(),
                color.getText().toString(), year.getText().toString(),plateNumber.getText().toString(), db );


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

    private void listOwner() {

        SQLiteDatabase db = helper.getWritableDatabase();

        listOwnerEntity =  OwnerDao.listOwner(db);

        if(listOwnerEntity!=null)
            listSpinnerOwner();
        else{
            listOwnerSpinner.add("Lista de Usuario");
        }


    }

    private void listSpinnerOwner() {

        listOwnerSpinner = new ArrayList<String>();
        listOwnerSpinner.add("Lista de Usuario");

        for (int i= 0; i<listOwnerEntity.size();i++){
            listOwnerSpinner.add(listOwnerEntity.get(i).getOwn_name() + "-" + listOwnerEntity.get(i).getCom_id()+ "-"
                    + listOwnerEntity.get(i).getOwn_type_identification());

        }
    }


    private void listarVehicle(){

        SQLiteDatabase db = helper.getWritableDatabase();
        listVehicleEntity =  VehicleDao.listVehicle(db);

        if(listVehicleEntity!=null)
            llenarSpinnerVehicle();
        else{
            listVehicleSpinner.add("Lista de Propiedades");
        }

    }

    private void llenarSpinnerVehicle() {

        listVehicleSpinner = new ArrayList<String>();
        listVehicleSpinner.add("Lista de Propiedades");

        for (int i= 0; i<listVehicleEntity.size();i++){
            listVehicleSpinner.add(listVehicleEntity.get(i).getVeh_id() + "-" + listVehicleEntity.get(i).getVeh_licenceplate() );

        }
    }



}
