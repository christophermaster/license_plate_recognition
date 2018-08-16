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
import android.widget.TextView;
import android.widget.Toast;

import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.ComunityDao;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class Comunity extends AppCompatActivity {


    private Button  create,
                    search,
                    delete,
                    update;

    private EditText id,
                     name,
                     type;

    private Spinner spinner;
    private TextView texto;


    private ArrayList<String> listaPersonas;
    private ArrayList<ComunityApi> listComunity;
    private ArrayAdapter<CharSequence> adaptador ;

    private BBDD_Helper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunity);


        create =(Button)findViewById(R.id.ingresar);
        search =(Button)findViewById(R.id.buscar);
        delete =(Button)findViewById(R.id.eliminar);
        update =(Button)findViewById(R.id.actualizar);

        id = (EditText)findViewById(R.id.id);
        name = (EditText)findViewById(R.id.nombre);
        type = (EditText)findViewById(R.id.tipo) ;


        helper = new BBDD_Helper(this);
        spinner = (Spinner)findViewById(R.id.spinner);
        texto = (TextView)findViewById(R.id.texto);

        listComunity();

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
        spinner.setAdapter(adaptador);

        //Cargar Lista de comunidad
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)
                    texto.setText(listComunity.get(position-1).getId().toString());
                else
                    texto.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    /**Create Comunity
     *
     * @param view
     *
     * */
    public void createComunity(View view){


        //Se crea la instancia
        SQLiteDatabase db = helper.getWritableDatabase();

        //instancia del modelo
        ComunityApi model = new ComunityApi();

        //set
        model.setNameComunity( name.getText().toString());
        model.setTypeComunity( type.getText().toString());
        model.setId(Integer.valueOf(id.getText().toString()));


        // se crea la comunidad
        Long newRowId = ComunityDao.createComunity(model,db);

        //Se verifica si se guardo el registro
        if(newRowId == -1){

            Toast.makeText(getApplicationContext(),"No se guardó el registro ", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(getApplicationContext(),"SE guardó el registro ", Toast.LENGTH_LONG).show();
            listComunity();
            adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
            spinner.setAdapter(adaptador);
        }


    }

    /**Update comunity
     *
     * @param view
     * */

    public void updateComunity(View view){

        //Se crea la instancia
        SQLiteDatabase db = helper.getWritableDatabase();

        //instancia del modelo
        ComunityApi model = new ComunityApi();

        //set
        model.setNameComunity( name.getText().toString());
        model.setTypeComunity( type.getText().toString());
        model.setId(Integer.valueOf(id.getText().toString()));

        //Se verifica si se actualizo el registro
        if(ComunityDao.updateComunity(model,  db) == 1){
            Toast.makeText(getApplicationContext(), "Se actualizó el registro" , Toast.LENGTH_LONG).show();

            listComunity();
            adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
            spinner.setAdapter(adaptador);

        }else{
            Toast.makeText(getApplicationContext(), "No se actualizó" , Toast.LENGTH_LONG).show();
        }


    }

    /**Delete comunity
     *
     * @param view
     * */

    public void deleteComunity(View view){

        //Se crea la instancia
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se verifica si se Elimino el registro
        if(ComunityDao.deleteComunity( id.getText().toString(), db ) == 1){

            Toast.makeText(getApplicationContext(), "Se borro el registro", Toast.LENGTH_LONG).show();

            id.setText("");
            name.setText("");
            type.setText("");

            listComunity();

            adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
            spinner.setAdapter(adaptador);

        }else{

            Toast.makeText(getApplicationContext(), "No se borro ningun registro ", Toast.LENGTH_LONG).show();

        }


    }


    private void listComunity() {
        //Se crea la instancia
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se obtine la lista de las comunidades
        listComunity = ComunityDao.listComunity(db);

        listaPersonas = new ArrayList<>();

        listaPersonas.add("Seleccione Comunidad");

        if(listComunity!=null){

            for (int i= 0; i<listComunity.size();i++){
                listaPersonas.add(listComunity.get(i).getNameComunity() + "-" + listComunity.get(i).getTypeComunity());

            }
        }


    }



    /**Search Comunity
     *
     * @param view
     * */
    public void buscar(View view) {

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getReadableDatabase();

        //Colocamos los campos que queremos que se muestre en la busqueda
        String[] projection = {
                //  Estructura_BBDD.COLUMNA_COMUNIDAD_ID,
                Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE,
                Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA
        };

        //condicion de busqueda
        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + " = ?";

        //valor de busqueda
        String[] selectionArgs = {id.getText().toString()};

        //String sortOrder = Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE + "DESC;";

        try {
            //Creacion de la consulta para la busqueda

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

            name.setText(c.getString(0));
            type.setText(c.getString(1));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No se encontró registro", Toast.LENGTH_LONG).show();

        }
    }
}
