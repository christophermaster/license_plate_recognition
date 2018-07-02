package com.sandro.openalprsample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.ComunityDao;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.entity.Comunity;
import com.sandro.openalprsample.entity.OwnerEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class Owner extends AppCompatActivity {


    private EditText idOwner,
                     nameOwner,
                     surnameOwner,
                     numberIdentity;

    private Button create,
                   update,
                   delete,
                   search;

    private Spinner idComunity,
                    typeIdentity,
                    listOwnerSpin;

    private BBDD_Helper helper ;

    private ArrayList<String> listComunitySpinner,
                              listOwnerSpinner,
                              listSpinnerTypeIdentity;


    private ArrayList<Comunity> listComunity;
    private ArrayList<OwnerEntity> listOwner;

    private ArrayAdapter<CharSequence> adaptadorComunity,
                                       adaptadorTypeIdentity,
                                       adaptadorOwner;

     private String selectTypeIdentity  = "";


    private Integer idcomuni = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        //EDITTEXT
        idOwner = (EditText)findViewById(R.id.idOwner);
        nameOwner = (EditText)findViewById(R.id.nameOwner);
        surnameOwner = (EditText)findViewById(R.id.surnameOwner);
        numberIdentity = (EditText)findViewById(R.id.numberIdentity);



        //SPINNER
        idComunity = (Spinner)findViewById(R.id.idComunity);
        typeIdentity = (Spinner)findViewById(R.id.typeIdentity);
        listOwnerSpin= (Spinner)findViewById(R.id.listOwner);

        //BUTTON

        create = (Button)findViewById(R.id.createOwner);
        search =(Button)findViewById(R.id.buscar);
        update =(Button)findViewById(R.id.actualizar);
        //BD
        helper = new BBDD_Helper(this);



        //EVENTOS CON SPINNER
        listComunity("Seleccione");
        adaptadorComunity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listComunitySpinner);
        idComunity.setAdapter(adaptadorComunity);

        idComunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)

                    idcomuni = listComunity.get(position-1).getIdComunity();

                else{
                    idcomuni = 0;
                    Toast.makeText(getApplicationContext(),"Elegir Comunidad", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listTypeIdentity("Seleccione");
        adaptadorTypeIdentity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listSpinnerTypeIdentity);
        typeIdentity.setAdapter(adaptadorTypeIdentity);
        typeIdentity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0)
                            selectTypeIdentity = listSpinnerTypeIdentity.get(position);
                        else{

                            selectTypeIdentity = "";
                            Toast.makeText(getApplicationContext(),"Elegir tipo de identificacion", Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        listOwner();
        adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
        listOwnerSpin.setAdapter(adaptadorOwner);
        listOwnerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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


        Long newRowId = OwnerDao.createOwner(nameOwner.getText().toString(), surnameOwner.getText().toString(),
                idcomuni, numberIdentity.getText().toString(), selectTypeIdentity, db );


        if(newRowId == -1){

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();

        }else{

            listOwner();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
            listOwnerSpin.setAdapter(adaptadorOwner);
            Toast.makeText(getApplicationContext(),"Se guardó el registro ", Toast.LENGTH_LONG).show();
            listOwner();

        }

    }

    public void update(View view) {

        SQLiteDatabase db = helper.getWritableDatabase();


        if(OwnerDao.updateOwner(idOwner.getText().toString(),nameOwner.getText().toString(), surnameOwner.getText().toString(),
                idcomuni, numberIdentity.getText().toString(), selectTypeIdentity, db) == 1){
            Toast.makeText(getApplicationContext(), "Se actualizó el registro" , Toast.LENGTH_LONG).show();

            listOwner();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
            listOwnerSpin.setAdapter(adaptadorOwner);

        }else{
            Toast.makeText(getApplicationContext(), "No se actualizó" , Toast.LENGTH_LONG).show();
        }
    }


    public void delete(View view){

        SQLiteDatabase db = helper.getWritableDatabase();

        if(OwnerDao.deleteOwner(idOwner.getText().toString(), db ) == 1){

            Toast.makeText(getApplicationContext(), "Se borro el registro", Toast.LENGTH_LONG).show();

            idOwner.setText("");

            listOwner();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
            listOwnerSpin.setAdapter(adaptadorOwner);

            nameOwner.setText("");
            surnameOwner.setText("");
            numberIdentity.setText("");


            listTypeIdentity("Seleccione");
            adaptadorTypeIdentity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listSpinnerTypeIdentity);
            typeIdentity.setAdapter(adaptadorTypeIdentity);

        }else{

            Toast.makeText(getApplicationContext(), "No se borro ningun registro ", Toast.LENGTH_LONG).show();

        }

    }



    public void buscar(View view) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                //  Estructura_BBDD.COLUMNA_COMUNIDAD_ID,
                Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,
                Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE,
                Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO,
                Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,
                Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD

        };

        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + " = ?";
        String[] selectionArgs = {idOwner.getText().toString()};


        try {

            Cursor c = db.query(

                    Estructura_BBDD.TABLA_PROPIETARIO,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );


            c.moveToFirst();
            listComunity(c.getString(0));
            adaptadorComunity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listComunitySpinner);
            idComunity.setAdapter(adaptadorComunity);
            nameOwner.setText(c.getString(1));
            surnameOwner.setText(c.getString(2));
            numberIdentity.setText(c.getString(3));
            listTypeIdentity(c.getString(4));
            adaptadorTypeIdentity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listSpinnerTypeIdentity);
            typeIdentity.setAdapter(adaptadorTypeIdentity);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se encontró registro", Toast.LENGTH_LONG).show();

        }
    }


    private void listOwner() {

        SQLiteDatabase db = helper.getWritableDatabase();

        listOwner =  OwnerDao.listOwner(db);

        if(listOwner!=null)
            listOwnerSpinner();
        else{
            listOwnerSpinner.add("Selecciones");
        }


    }

    private void listOwnerSpinner() {

        listOwnerSpinner = new ArrayList<String>();
        listOwnerSpinner.add("Selecciones");

        for (int i= 0; i<listOwner.size();i++){
            listOwnerSpinner.add(listOwner.get(i).getNameOwner() + "-" + listOwner.get(i).getIdcommunity()+ "-"
            + listOwner.get(i).getTypeIdentity());

        }
    }

    private void listComunity(String valor) {

        SQLiteDatabase db = helper.getWritableDatabase();

        listComunity = ComunityDao.listComunity(db);

        if(listComunity!=null)
            listSpinnerComunity(valor);
        else{
            listComunitySpinner.add("Selecciones");
        }


    }

    private void listSpinnerComunity(String valor) {

        listComunitySpinner = new ArrayList<String>();
        listComunitySpinner.add(valor);

        for (int i= 0; i<listComunity.size();i++){
            if(valor != listComunity.get(i).getNameComunity() )
            listComunitySpinner.add(listComunity.get(i).getNameComunity() + "-" + listComunity.get(i).getTypeComunity());

        }
    }

    private void listTypeIdentity(String valor){
        listSpinnerTypeIdentity = new ArrayList<String>();

        listSpinnerTypeIdentity.add(valor);

        if (valor != "V")
             listSpinnerTypeIdentity.add("V");
        if (valor != "E")
             listSpinnerTypeIdentity.add("E");
        if (valor != "J")
            listSpinnerTypeIdentity.add("J");
    }


}
