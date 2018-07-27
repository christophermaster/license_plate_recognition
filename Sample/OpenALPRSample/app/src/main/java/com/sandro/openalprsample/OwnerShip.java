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
import com.sandro.openalprsample.entity.OwnerEntity;
import com.sandro.openalprsample.entity.OwnerShipEntity;

import java.util.ArrayList;

public class OwnerShip extends AppCompatActivity {



    private BBDD_Helper helper ;

    private EditText area,
            idOwnership,
            inhabited,
            number,
            type;

    private Spinner spinnerNameOwner ;
    private Spinner spinnerOwnership;

    private ArrayAdapter<CharSequence> adaptadorOwner;

    private ArrayList<OwnerEntity> listOwnerEntity;
    private ArrayList<OwnerShipEntity> ListOwnershipEntity;

    private ArrayList<String>  listOwnerSpinner ;
    private ArrayList<String>  listOwnershipSpinner ;




    private Integer idOwner = 0 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_owner_ship);

        idOwnership = (EditText)findViewById(R.id.idOwnership);
        area = (EditText)findViewById(R.id.area);
        inhabited = (EditText)findViewById(R.id.inhabited);
        number = (EditText)findViewById(R.id.number);
        type= (EditText)findViewById(R.id.type);


        spinnerNameOwner= (Spinner)findViewById(R.id.nameOwner);
        spinnerOwnership = (Spinner)findViewById(R.id.listOwnership);

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



        listarOwnership();
        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnershipSpinner);
        spinnerOwnership.setAdapter(adaptadorOwner);
        spinnerOwnership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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


        Long newRowId = PropertyDao.createOwnership(area.getText().toString(), inhabited.getText().toString(),
                idOwner, number.getText().toString(), type.getText().toString(), db );


        if(newRowId == -1){

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();

        }else{

            listarOwnership();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnershipSpinner);
            spinnerOwnership.setAdapter(adaptadorOwner);
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


    private void listarOwnership(){

        SQLiteDatabase db = helper.getWritableDatabase();
        ListOwnershipEntity =  PropertyDao.listOwnerShip(db);

        if(ListOwnershipEntity!=null)
            llenarSpinnerOwnership();
        else{
            listOwnershipSpinner.add("Lista de Propiedades");
        }

    }

    private void llenarSpinnerOwnership() {

        listOwnershipSpinner = new ArrayList<String>();
        listOwnershipSpinner.add("Lista de Propiedades");

        for (int i= 0; i<ListOwnershipEntity.size();i++){
            listOwnershipSpinner.add(ListOwnershipEntity.get(i).getOsh_inhabited() + "-" + ListOwnershipEntity.get(i).getOsh_number()+ "-"
                    + ListOwnershipEntity.get(i).getOsh_area() +  "-" + ListOwnershipEntity.get(i).getOsh_type() );

        }
    }







}


