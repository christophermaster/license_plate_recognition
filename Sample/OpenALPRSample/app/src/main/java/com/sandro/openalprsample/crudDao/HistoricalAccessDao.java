package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.apiRest.models.AccessHistoryApi;
import com.sandro.openalprsample.entity.HistoricalAccessEntity;
import com.sandro.openalprsample.entity.VehicleEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.sql.Blob;
import java.util.ArrayList;

public class HistoricalAccessDao {

    private static ArrayList<HistoricalAccessEntity> listAccess;


    /**New Access
     * @param model
     * */
    public static Long createAccess(AccessHistoryApi model, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO,model.getIdOwner());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDCOMUNIDAD,model.getCom_id());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FECHA, model.getDate());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_HORA, model.getHour());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO,model.getTypeaccess());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA,model.getTypesecurity());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FOTO,model.getPhotho());

        //Se insertan
        Long newRowId = db.insert(Estructura_BBDD.TABLA_HISTORICO,null,values);

        return newRowId;

    }


    /**Update Access
     *
     * @param model
     *
     * */
    public static Integer updateAccess(AccessHistoryApi model, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO,model.getIdOwner());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDCOMUNIDAD,model.getCom_id());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FECHA, model.getDate());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_HORA, model.getHour());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO,model.getTypeaccess());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA,model.getTypesecurity());
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FOTO,model.getPhotho());

        //Condicion de actualizacion
        String selection = Estructura_BBDD.COLUMNA_HISTORICO_ID + "= ?";

        //Valor del elemento que se va actualizar
        String[] selectionArgs = {model.getId().toString()};

        //Se actualiza
        int count = db.update(
                Estructura_BBDD.TABLA_HISTORICO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }

    /**Delete Access
     *
     * @param id
     * @param db
     *
     * */

    public static Integer deleteAccess(String id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_HISTORICO_ID + " = ?";
        String[] selectionArgs = {id};

        int count = db.delete(Estructura_BBDD.TABLA_HISTORICO,selection,selectionArgs);
        return count;


    }


    /**List ALl Owner
     *
     * @param db
     *
     * */


    public static ArrayList<HistoricalAccessEntity> listVehicle(SQLiteDatabase db){

        HistoricalAccessEntity comu;
        listAccess = new ArrayList<HistoricalAccessEntity>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_HISTORICO,null);

        while (c.moveToNext()){

            comu  = new HistoricalAccessEntity();
            comu.setIdHistorical(c.getInt(0));
            comu.setIdOwner(c.getInt(1));
            comu.setDate(c.getString(2));
            comu.setHour(c.getString(3));
            comu.setTypeAccess(c.getString(4));
            comu.setTypeSecuriry(c.getString(5));
            comu.setImage(c.getBlob(6));


            listAccess.add(comu);

        }

        return listAccess;
    }



}
