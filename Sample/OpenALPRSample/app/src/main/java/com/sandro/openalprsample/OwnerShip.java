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
import com.sandro.openalprsample.apiRest.models.OwnershipApi;
import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.crudDao.PropertyDao;


import java.util.ArrayList;

public class OwnerShip extends AppCompatActivity {




    //EDIT TEXT
    private EditText area,
            idOwnership,
            inhabited,
            number,
            type;

    //SPINNER
    private Spinner spinnerNameOwner ;
    private Spinner spinnerOwnership;

    //ADAPTARORES DE LOS SPINNER
    private ArrayAdapter<CharSequence> adaptadorOwner;

    //LISTA QUE SE USA PARA CONTENER LOS MODELOS
    private ArrayList<OwnerApi> listOwnerEntity;
    private ArrayList<OwnershipApi> ListOwnershipEntity;

    //LISTA QUE SE USA PARA LLENAR LOS SPINNER
    private ArrayList<String>  listOwnerSpinner ;
    private ArrayList<String>  listOwnershipSpinner ;

    //HELPER DE LA BASE DE DATOS
    private BBDD_Helper helper ;

    // SE USA PARA SABER QUE ELEMENTO FUE SELECCIONADO EN EL SPINNER
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


    /**Create Ownership
     *
     * @param view
     * */
    public void  create(View view){

        //Se instancia la bd
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia el modelo
        OwnershipApi model = new OwnershipApi();

        //Se almacena los datos  al modelo
        model.setArea(Double.valueOf(area.getText().toString()));
        model.setInhabited(Boolean.valueOf(inhabited.getText().toString()));
        model.setOwnershipNumber(number.getText().toString());
        model.setTypeOwnership(type.getText().toString());

        //Se crea el nuevo registro
        Long newRowId = PropertyDao.createOwnership(model,idOwner, db );

        //Se verefica si el registro se guardo o no correctamente
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

    /**List all ownership in spinner
     *
     * */
    private void listarOwnership(){

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia la lista que se utulizara para mostrar lo elementos en el Spinner
        listOwnershipSpinner = new ArrayList<>();

        //Se almacena ls lista de propiedades
        ListOwnershipEntity =  PropertyDao.listOwnerShip(db);

        //Agregamos el primer elemento que se mostrara en el Spinner
        listOwnershipSpinner.add("Lista de propiedades");

        //Se verifica si existe propietarios o no
        if(ListOwnershipEntity!=null)
            for (int i= 0; i<ListOwnershipEntity.size();i++){
                //Se llena el Spinner
                listOwnershipSpinner.add(ListOwnershipEntity.get(i).getInhabited() + "-" + ListOwnershipEntity.get(i).getOwnershipNumber()+ "-"
                        + ListOwnershipEntity.get(i).getArea() +  "-" + ListOwnershipEntity.get(i).getTypeOwnership() );

            }

    }


}


