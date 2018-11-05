package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.apiRest.models.VehicleApi;

import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class VehicleDao {

    private static ArrayList<VehicleApi> listVehicle;


    /**New Vehicle
     *
     * @param model
     * @param db
     * @param idOwner
     *
     * */
    public static Long createVehicle(VehicleApi model ,Integer idOwner, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MARCA, model.getMakeVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MODELO, model.getModelVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_COLOR,model.getColourVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_AÑO,model.getLongVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER,model.getLecenseplateVehicle());

        //Se insertan
        Long newRowId = db.insert(Estructura_BBDD.TABLA_VEHICULO,null,values);

        return newRowId;

    }


    /**Update Vehicle
     *
     * @param model
     * @param idOwner
     * */

    public static Integer updateVehicle(VehicleApi model ,Integer idOwner, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO,idOwner);
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MARCA, model.getMakeVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_MODELO, model.getModelVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_COLOR,model.getColourVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_AÑO,model.getLongVehicle());
        values.put(Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER,model.getLecenseplateVehicle());

        //Condicion de actualizacion
        String selection = Estructura_BBDD.COLUMNA_VEHICULO_ID + "= ?";

        //Valor del elemento que se va actualizar
        String[] selectionArgs = {model.getId().toString()};

        //Se actualiza
        int count = db.update(
                Estructura_BBDD.TABLA_VEHICULO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteVehicle(String id, SQLiteDatabase db){

        //Condicion para eliminar el elemento
        String selection = Estructura_BBDD.COLUMNA_VEHICULO_ID + " = ?";

        //Valor del elemento que se va a eliminar
        String[] selectionArgs = {id};

        //Se elimina el elemento
        int count = db.delete(Estructura_BBDD.TABLA_VEHICULO,selection,selectionArgs);

        return count;


    }

    /**List ALl Vehicle
     *
     * @param db
     *
     * */

    public static ArrayList<VehicleApi> listVehicle(SQLiteDatabase db){

        VehicleApi comu;
        listVehicle = new ArrayList<VehicleApi>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_VEHICULO,null);

        while (c.moveToNext()){

            comu  = new VehicleApi();
            comu.setId(c.getInt(0));
            comu.setMakeVehicle(c.getString(2));
            comu.setModelVehicle(c.getString(3));
            comu.setColourVehicle(c.getString(4));
            comu.setLongVehicle(c.getString(5));
            comu.setLecenseplateVehicle(c.getString(6));

            listVehicle.add(comu);

        }

        return listVehicle;
    }

    /**Exists Vehicle
     * @param db
     * @param number
     *
     * */

    public static Boolean exists(SQLiteDatabase db, String number){

        //Verificar si existe registro con la placa introducida
        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_VEHICULO + " WHERE " +
                Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER + " = " + "'" + number + "'",null);

        //verificamos si coincidencia o no
        if(c.moveToNext()){
            c.close();
            return true;
        }

        return false;
    }

    public static  Integer idOwner(SQLiteDatabase db, String placa){
        //Verificar si existe registro con la placa introducida
        Cursor c = db.rawQuery("SELECT OWN_ID FROM " + Estructura_BBDD.TABLA_VEHICULO + " WHERE " +
                Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER + " = " + "'" + placa + "'",null);

        if(c.moveToNext()){

            return c.getInt(0);
        }

        return 0;
    }

}
