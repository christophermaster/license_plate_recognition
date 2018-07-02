package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.entity.VehicleEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class VehicleDao {

    private static ArrayList<VehicleEntity> listVehicle;


    public static Long createVehicle(Integer idOwner, String make ,String pattern, String color,
                                     String year, String plateNumber, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MARCA, make);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MODELO, pattern);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_COLOR,color);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_AÑO,year);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER,plateNumber);

        Long newRowId = db.insert(Estructura_BBDD.TABLA_VEHICULO,null,values);

        return newRowId;

    }



    public static Integer updateVehicle(String id, Integer idOwner, String make ,String pattern, String color,
                                        String year, String plateNumber, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MARCA, make);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MODELO, pattern);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_COLOR,color);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_AÑO,year);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER,plateNumber);

        String selection = Estructura_BBDD.COLUMNA_VEHICULO_ID + "= ?";
        String[] selectionArgs = {id};

        int count = db.update(
                Estructura_BBDD.TABLA_VEHICULO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteVehicle(String id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_VEHICULO_ID + " = ?";
        String[] selectionArgs = {id};

        int count = db.delete(Estructura_BBDD.TABLA_VEHICULO,selection,selectionArgs);
        return count;


    }


    public static ArrayList<VehicleEntity> listVehicle(SQLiteDatabase db){

        VehicleEntity comu = null;
        listVehicle = new ArrayList<VehicleEntity>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_VEHICULO,null);

        while (c.moveToNext()){

            comu  = new VehicleEntity();
            comu.setIdVehicle(c.getInt(0));
            comu.setIdOwner(c.getInt(1));
            comu.setMake(c.getString(2));
            comu.setPattern(c.getString(3));
            comu.setColor(c.getString(4));
            comu.setYear(c.getInt(5));
            comu.setPlateNumber(c.getString(6));

            listVehicle.add(comu);

        }

        return listVehicle;
    }

}
