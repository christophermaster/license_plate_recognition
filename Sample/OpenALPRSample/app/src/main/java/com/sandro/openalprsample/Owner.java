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

import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.apiRest.models.OwnerApi;
import com.sandro.openalprsample.conexion.BBDD_Helper;
import com.sandro.openalprsample.crudDao.ComunityDao;
import com.sandro.openalprsample.crudDao.OwnerDao;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class Owner extends AppCompatActivity {


    //EDIT TEXT
    private EditText idOwner,
                     nameOwner,
                     surnameOwner,
                     numberIdentity;

    //BUTTON
    private Button create,
                   update,
                   delete,
                   search;

    //SPINNER
    private Spinner idComunity,
                    typeIdentity,
                    listOwnerSpin;

    //HELPER DE LA BASE DE DATOS
    private BBDD_Helper helper ;

    //LISTA QUE SE USA PARA LLENAR LOS SPINNER
    private ArrayList<String> listComunitySpinner,
                              listOwnerSpinner,
                              listSpinnerTypeIdentity;


    //LISTA QUE SE USA PARA CONTENER LOS MODELOS
    private ArrayList<ComunityApi> listComunity;
    private ArrayList<OwnerApi> listOwner;

    //ADAPTARORES DE LOS SPINNER
    private ArrayAdapter<CharSequence> adaptadorComunity,
                                       adaptadorTypeIdentity,
                                       adaptadorOwner;

     private String selectTypeIdentity  = "";

    // SE USA PARA SABER QUE ELEMENTO FUE SELECCIONADO EN EL SPINNER
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
        listComunity();
        adaptadorComunity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listComunitySpinner);
        idComunity.setAdapter(adaptadorComunity);

        idComunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0)

                    idcomuni = listComunity.get(position-1).getId();

                else{
                    idcomuni = 0;
                    Toast.makeText(getApplicationContext(),"Elegir Comunidad", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listTypeIdentity("Tipo de Identificación");
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

    /**Create Owner
     *
     * @param view
     * */

    public void  create(View view){

        //Se instancia la bd
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia el modelo
        OwnerApi model = new OwnerApi();

        //Se almacena los datos  al modelo
        model.setNameOwner(nameOwner.getText().toString());
        model.setLastNameOwner(surnameOwner.getText().toString());
        model.setIdentificationNumberOwner(numberIdentity.getText().toString());
        model.setTypeIdentificationNumberOwner(selectTypeIdentity);

        //Se crea el nuevo registro
        Long newRowId = OwnerDao.createOwner(model, idcomuni,db );

        //Se verefica si el registro se guardo o no correctamente
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

    /**Update Owner
     *
     * @param view
     * */

    public void update(View view) {

        //Se instancia la bd
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia el modelo
        OwnerApi model = new OwnerApi();

        //Se almacena los datos  al modelo
        model.setNameOwner(nameOwner.getText().toString());
        model.setLastNameOwner(surnameOwner.getText().toString());
        model.setIdentificationNumberOwner(numberIdentity.getText().toString());
        model.setTypeIdentificationNumberOwner(selectTypeIdentity);

        //Se verefica si el registro fue modificado o no correctamente
        if(OwnerDao.updateOwner(model, idcomuni,db) == 1){
            Toast.makeText(getApplicationContext(), "Se actualizó el registro" , Toast.LENGTH_LONG).show();

            listOwner();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
            listOwnerSpin.setAdapter(adaptadorOwner);

        }else{
            Toast.makeText(getApplicationContext(), "No se actualizó" , Toast.LENGTH_LONG).show();
        }
    }

    /**Update Owner
     *
     * @param view
     * */

    public void delete(View view){

        //Se instancia la bd
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se verefica si el registro fue modificado o no correctamente
        if(OwnerDao.deleteOwner(idOwner.getText().toString(), db ) == 1){

            Toast.makeText(getApplicationContext(), "Se borro el registro", Toast.LENGTH_LONG).show();

            // se resetea todos los campos
            idOwner.setText("");

            listOwner();
            adaptadorOwner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listOwnerSpinner);
            listOwnerSpin.setAdapter(adaptadorOwner);

            nameOwner.setText("");
            surnameOwner.setText("");
            numberIdentity.setText("");


            listTypeIdentity("Tipo De Identificación");
            adaptadorTypeIdentity = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listSpinnerTypeIdentity);
            typeIdentity.setAdapter(adaptadorTypeIdentity);

        }else{

            Toast.makeText(getApplicationContext(), "No se borro ningun registro ", Toast.LENGTH_LONG).show();

        }

    }


    /**Search Owner
     *
     * @param view
     * */

    public void buscar(View view) {

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getReadableDatabase();

        //Colocamos los campos que queremos que se muestre en la busqueda
        String[] projection = {
                //  Estructura_BBDD.COLUMNA_COMUNIDAD_ID,
                Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,
                Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE,
                Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO,
                Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,
                Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD

        };

        //condicion de busqueda
        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + " = ?";

        //valor de busqueda
        String[] selectionArgs = {idOwner.getText().toString()};


        try {
            //Creacion de la consulta para la busqueda

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
            listComunitySpinner.add(c.getString(0));
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

    /**List all owner in spinner
     *
     * */

    private void listOwner() {

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia la lista que se utulizara para mostrar lo elementos en el Spinner
        listOwnerSpinner = new ArrayList<>();

        //Se instancia la lista que tendra los propietarios
        listOwner = new ArrayList<>();

        //Se almacena la lista de propietarios
        listOwner =  OwnerDao.listOwner(db);

        //Agregamos el primer elemento que se mostrara en el Spinner
        listOwnerSpinner.add("Lista de Propietarios");

        //Se verifica si existe propietarios o no
        if(listOwner!=null)
            //Se llena el Spinner
            for (int i= 0; i<listOwner.size();i++){
                listOwnerSpinner.add(listOwner.get(i).getNameOwner() + "-"
                        + listOwner.get(i).getTypeIdentificationNumberOwner());

            }


    }

    /**List all Comunity in spinner
     *
     * */


    private void listComunity() {

        //Se instancia la base de datos
        SQLiteDatabase db = helper.getWritableDatabase();

        //Se instancia la lista que se utulizara para mostrar lo elementos en el Spinner
        listComunitySpinner = new ArrayList<>();

        //Se instancia la lista que tendra las comunidades
        listComunity =  new ArrayList<>();

        //Se almacena la lista de comunidad
        listComunity = ComunityDao.listComunity(db);

        //Agregamos el primer elemento que se mostrara en el Spinner
        listComunitySpinner.add("Seleccione Comunidad");

        //Se verifica si existe propietarios o no
        if(listComunity!=null)
            //Se llena el Spinner
            for (int i= 0; i<listComunity.size();i++){

               listComunitySpinner.add(listComunity.get(i).getNameComunity() + "-"
                       + listComunity.get(i).getTypeComunity());

            }


    }



    private void listTypeIdentity(String valor){
        listSpinnerTypeIdentity = new ArrayList<>();

        listSpinnerTypeIdentity.add(valor);

        if (valor != "V")
             listSpinnerTypeIdentity.add("V");
        if (valor != "E")
             listSpinnerTypeIdentity.add("E");
        if (valor != "J")
            listSpinnerTypeIdentity.add("J");
    }


}
