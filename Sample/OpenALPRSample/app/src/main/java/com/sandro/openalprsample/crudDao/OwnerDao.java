package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.apiRest.models.OwnerApi;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class OwnerDao {

    private static ArrayList<OwnerApi> listOwner;

    /**New Owner
     *
     * @param model
     * @param db
     * @param idComunity
     *
     * */

    public static Long createOwner(OwnerApi model , Integer idComunity, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,idComunity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE, model.getNameOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO, model.getLastNameOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,model.getIdentificationNumberOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD,model.getTypeIdentificationNumberOwner());

        //Se insertan
        Long newRowId = db.insert(Estructura_BBDD.TABLA_PROPIETARIO,null,values);

        return newRowId;

    }

    /**Update Owner
     *
     * @param model
     * @param idComunity
     * */

    public static Integer updateOwner(OwnerApi model , Integer idComunity, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,idComunity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE, model.getNameOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO, model.getLastNameOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,model.getIdentificationNumberOwner());
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD,model.getTypeIdentificationNumberOwner());

        //Condicion de actualizacion
        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + "= ?";

        //Valor del elemento que se va actualizar
        String[] selectionArgs = {model.getId().toString()};

        //Se actualiza
        int count = db.update(
                Estructura_BBDD.TABLA_PROPIETARIO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }

    /**Delete Owner
     *
     * @param id
     * @param db
     *
     * */

    public static Integer deleteOwner(String id, SQLiteDatabase db){

        //Condicion para eliminar el elemento
        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + " = ?";
        //Valor del elemento que se va a eliminar
        String[] selectionArgs = {id};

        //Se elimina el elemento
        int count = db.delete(Estructura_BBDD.TABLA_PROPIETARIO,selection,selectionArgs);

        return count;


    }

    /**List ALl Owner
     *
     * @param db
     *
     * */


    public static ArrayList<OwnerApi> listOwner(SQLiteDatabase db){

        OwnerApi comu = null;
        listOwner = new ArrayList<OwnerApi>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIETARIO,null);

        while (c.moveToNext()){

            comu  = new OwnerApi();

            comu.setId(c.getInt(0));
          //  comu.setCom_id(c.getInt(1));
            comu.setNameOwner(c.getString(2));
            comu.setLastNameOwner(c.getString(3));
            comu.setIdentificationNumberOwner(c.getString(4));
            comu.setTypeIdentificationNumberOwner(c.getString(5));

            listOwner.add(comu);

        }

        return listOwner;
    }


    /**Exists Owner
     * @param db
     * @param cedula
     *
     * */
    public static Boolean exists(SQLiteDatabase db, String cedula ){

        //Verificar si existe registro con la cedula introducida
        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIETARIO + " WHERE " +
                Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD + " = " + cedula,null);

        //verificamos si coincidencia o no
        if(c.moveToNext()){
            return true;
        }

        return false;
    }



}
