package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.apiRest.models.OwnershipApi;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class PropertyDao {

    private static ArrayList<OwnershipApi> listOwner;


    /**New Owner
     *
     * @param model
     * @param db
     * @param idOwner
     *
     * */
    public static Long createOwnership(OwnershipApi model, Integer idOwner, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_AREA,model.getArea());

        if( model.getInhabited())
            values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO, 1);
        else
            values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO, 0);

        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD, model.getOwnershipNumber());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO,model.getTypeOwnership());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO,idOwner);

        //Se insertan
        Long newRowId = db.insert(Estructura_BBDD.TABLA_PROPIEDAD,null,values);

        return newRowId;

    }


    /**Update OwnerShip
     *
     * @param model
     * @param idOwner
     * */
    public static Integer updateOwnership(OwnershipApi model, Integer idOwner, SQLiteDatabase db){

        //Se instancia el Objeto para insertar elementos a la base de datos
        ContentValues values = new ContentValues();

        //Se almacena los resultados temporalmente
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_AREA,model.getArea());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO, model.getInhabited());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD, model.getOwnershipNumber());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO,model.getTypeOwnership());
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO,idOwner);

        //Condicion de actualizacion
        String selection = Estructura_BBDD.COLUMNA_PROPIEDAD_ID + "= ?";

        //Valor del elemento que se va actualizar
        String[] selectionArgs = {model.getId().toString()};

        //Se actualiza
        int count = db.update(
                Estructura_BBDD.TABLA_PROPIEDAD,
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
    public static Integer deleteOwnership(String id, SQLiteDatabase db){

        //Condicion para eliminar el elemento
        String selection = Estructura_BBDD.COLUMNA_PROPIEDAD_ID  + " = ?";

        //Valor del elemento que se va a eliminar
        String[] selectionArgs = {id};

        //Se elimina el elemento
        int count = db.delete(Estructura_BBDD.TABLA_PROPIEDAD,selection,selectionArgs);

        return count;


    }

    /**List ALl Owner
     *
     * @param db
     *
     * */
    public static ArrayList<OwnershipApi> listOwnerShip(SQLiteDatabase db){

        OwnershipApi comu;
        listOwner = new ArrayList<OwnershipApi>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIEDAD,null);



        while (c.moveToNext()){

            comu  = new OwnershipApi();

            comu.setId(c.getInt(0));
            comu.setArea(c.getDouble(5));
            if(c.getInt(4) == 1)
                comu.setInhabited(true);
            else
                comu.setInhabited(false);
            comu.setOwnershipNumber(c.getString(2));
            comu.setTypeOwnership(c.getString(3));


            listOwner.add(comu);

        }

        return listOwner;
    }

    /**Exists Owner
     * @param db
     * @param number
     *
     * */
    public static Boolean exists(SQLiteDatabase db, String number ){

        //Verificar si existe registro con la cedula introducida
        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIEDAD + " WHERE " +
                Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD + " = " + "'"+ number + "'",null);

        //verificamos si coincidencia o no
        if(c.moveToNext()){
            return true;
        }

        return false;
    }





}
