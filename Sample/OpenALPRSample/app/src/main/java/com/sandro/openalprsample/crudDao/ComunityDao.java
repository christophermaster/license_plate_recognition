package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.apiRest.models.ComunityApi;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;


public class ComunityDao {

    private static ArrayList<ComunityApi> listComunity;


    /**New Comunity
     *
     * @param model
     * @param db
     *
     * */
    public static Long createComunity(ComunityApi model, SQLiteDatabase db){


        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE, model.getNameComunity());
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA, model.getTypeComunity());

        //Se insertan
        Long newRowId = db.insert(Estructura_BBDD.TABLA_COMUNIDAD,null,values);


        return newRowId;
    }

    /**Update Comunity
     *
     * @param model
     *
     * */

    public static Integer updateComunity(ComunityApi model, SQLiteDatabase db){


        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE,model.getNameComunity());
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA,model.getTypeComunity());

        //Condicion de actualizacion
        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + "= ?";

        //Valor del elemento que se va actualizar
        String[] selectionArgs = {model.getId().toString()};

        //Se actualiza
        int count = db.update(
                Estructura_BBDD.TABLA_COMUNIDAD,
                values,
                selection,
                selectionArgs
        );


        return count;
    }

    /**Delete comunidad
     *
     * @param id
     * @param db
     *
     * */

    public static Integer deleteComunity(String id, SQLiteDatabase db){

        //Condicion para eliminar el elemento
        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + " = ?";

        //Valor del elemento que se va a eliminar
        String[] selectionArgs = {id};

        //Se elimina el elemento
        int count = db.delete(Estructura_BBDD.TABLA_COMUNIDAD,selection,selectionArgs);

        return count;


    }

    /**List ALl Comunity
     *
     * @param db
     *
     * */

    public static ArrayList<ComunityApi> listComunity(SQLiteDatabase db){

        ComunityApi comu = new ComunityApi();

        listComunity = new ArrayList<ComunityApi>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_COMUNIDAD,null);

        while (c.moveToNext()){

            comu  = new ComunityApi();

            comu.setId(c.getInt(0));
            comu.setNameComunity(c.getString(1));
            comu.setTypeComunity(c.getString(2));

            listComunity.add(comu);

        }

        return listComunity;
    }



}
