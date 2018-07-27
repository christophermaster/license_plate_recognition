package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.entity.HistoricalAccessEntity;
import com.sandro.openalprsample.entity.VehicleEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.sql.Blob;
import java.util.ArrayList;

public class HistoricalAccessDao {

    private static ArrayList<HistoricalAccessEntity> listAccess;


    public static Long createAccess(Integer idOwner, String date , String hour, String typeAccess,
                                    String typeSecurity, byte[] photo, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FECHA, date);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_HORA, hour);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO,typeAccess);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA,typeSecurity);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FOTO,photo);

        Long newRowId = db.insert(Estructura_BBDD.TABLA_HISTORICO,null,values);

        return newRowId;

    }



    public static Integer updateVehicle(String id, Integer idOwner, String date , String hour, String typeAccess,
                                        String typeSecurity, byte[] photo, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FECHA, date);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_HORA, hour);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO,typeAccess);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA,typeSecurity);
        values.put(Estructura_BBDD.COLUMNA_HISTORICO_FOTO,photo);

        String selection = Estructura_BBDD.COLUMNA_HISTORICO_ID + "= ?";
        String[] selectionArgs = {id};

        int count = db.update(
                Estructura_BBDD.TABLA_HISTORICO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteVehicle(String id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_HISTORICO_ID + " = ?";
        String[] selectionArgs = {id};

        int count = db.delete(Estructura_BBDD.TABLA_HISTORICO,selection,selectionArgs);
        return count;


    }


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
