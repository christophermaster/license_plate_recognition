package com.sandro.openalprsample;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.ComunityDao;
import com.sandro.openalprsample.entity.Comunity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class Create extends AppCompatActivity {


    private Button crear, buscar,eliminar,actualizar;
    private EditText id, nombre, tipo;
    private Spinner spinner;
    private TextView texto;
    private ComunityDao comunityDao;
    private BBDD_Helper helper ;
    private ArrayList<String> listaPersonas;
    private ArrayList<Comunity> comunidad;
    private ArrayAdapter<CharSequence> adaptador ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        crear =(Button)findViewById(R.id.ingresar);
        buscar =(Button)findViewById(R.id.buscar);
        eliminar =(Button)findViewById(R.id.eliminar);
        actualizar =(Button)findViewById(R.id.actualizar);

        id = (EditText)findViewById(R.id.id);
        nombre = (EditText)findViewById(R.id.nombre);
        tipo = (EditText)findViewById(R.id.tipo) ;


        helper = new BBDD_Helper(this);
        spinner = (Spinner)findViewById(R.id.spinner);
        texto = (TextView)findViewById(R.id.texto);

        consultarComunidad();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(position!=0)
                     texto.setText(comunidad.get(position-1).getIdComunity().toString());
                 else
                     texto.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    public void createComunity(View view){

        SQLiteDatabase db = helper.getWritableDatabase();

        Long newRowId = ComunityDao.createComunity(nombre,tipo,db);


        if(newRowId == -1){

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();
            consultarComunidad();
            adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
            spinner.setAdapter(adaptador);
        }


    }


    public void updateComunity(View view){

        SQLiteDatabase db = helper.getWritableDatabase();


        if(ComunityDao.updateComunity( nombre,  tipo,  id,  db) == 1){
            Toast.makeText(getApplicationContext(), "Se actualizó el registro" , Toast.LENGTH_LONG).show();

            consultarComunidad();
            adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
            spinner.setAdapter(adaptador);

        }else{
            Toast.makeText(getApplicationContext(), "No se actualizó" , Toast.LENGTH_LONG).show();
        }


    }


    public void deleteComunity(View view){

        SQLiteDatabase db = helper.getWritableDatabase();

       if(ComunityDao.deleteComunity( id, db ) == 1){

           Toast.makeText(getApplicationContext(), "Se borro el registro", Toast.LENGTH_LONG).show();

           id.setText("");
           nombre.setText("");
           tipo.setText("");

           consultarComunidad();

           adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
           spinner.setAdapter(adaptador);

       }else{

           Toast.makeText(getApplicationContext(), "No se borro ningun registro ", Toast.LENGTH_LONG).show();

       }


    }

    
    private void consultarComunidad() {

        SQLiteDatabase db = helper.getWritableDatabase();

       comunidad = ComunityDao.listComunity(db);

       if(comunidad!=null)
            obtenerlLista();
       else{
           listaPersonas.add("Selecciones");
       }


    }

    private void obtenerlLista() {

        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Selecciones");

        for (int i= 0; i<comunidad.size();i++){
            listaPersonas.add(comunidad.get(i).getNameComunity() + "-" + comunidad.get(i).getTypeComunity());

        }
    }




    public void buscar(View view) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                //  Estructura_BBDD.COLUMNA_COMUNIDAD_ID,
                Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE,
                Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA
        };

        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + " = ?";
        String[] selectionArgs = {id.getText().toString()};

        String sortOrder = Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE + "DESC;";

        try {

            Cursor c = db.query(

                    Estructura_BBDD.TABLA_COMUNIDAD,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );


            c.moveToFirst();

            nombre.setText(c.getString(0));
            tipo.setText(c.getString(1));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se encontró registro", Toast.LENGTH_LONG).show();

        }
    }




}


