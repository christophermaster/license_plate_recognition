package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.entity.OwnerEntity;
import com.sandro.openalprsample.entity.OwnerShipEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class PropertyDao {

    private static ArrayList<OwnerShipEntity> listOwner;


    public static Long createOwnership(String area, String inhabited ,Integer idOwner,
                                   String number, String type,SQLiteDatabase db){

        System.out.print(area+inhabited+idOwner+number+type);
        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_AREA,area);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO, inhabited);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD, number);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO,type);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO,idOwner);

        Long newRowId = db.insert(Estructura_BBDD.TABLA_PROPIEDAD,null,values);

        return newRowId;

    }



    public static Integer updateOwnership(String id,String area, String inhabited ,Integer idOwner,
                                          String number, String type,SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_AREA,area);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO, inhabited);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD, number);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO,type);
        values.put(Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO,idOwner);

        String selection = Estructura_BBDD.COLUMNA_PROPIEDAD_ID + "= ?";
        String[] selectionArgs = {id};

        int count = db.update(
                Estructura_BBDD.TABLA_PROPIEDAD,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteOwnership(String id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_PROPIEDAD_ID  + " = ?";
        String[] selectionArgs = {id};

        int count = db.delete(Estructura_BBDD.TABLA_PROPIEDAD,selection,selectionArgs);
        return count;


    }


    public static ArrayList<OwnerShipEntity> listOwnerShip(SQLiteDatabase db){

        OwnerShipEntity comu;
        listOwner = new ArrayList<OwnerShipEntity>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIEDAD,null);



        while (c.moveToNext()){

            comu  = new OwnerShipEntity();

            comu.setOsh_id(c.getInt(0));
            comu.setOwn_id(c.getInt(1));
            comu.setOsh_area(c.getDouble(5));
            comu.setOsh_inhabited(c.getInt(4));
            comu.setOsh_number(c.getString(2));
            comu.setOsh_type(c.getString(3));
            System.out.println("idP: "+c.getInt(0)+ " idS: "+c.getInt(1)
            +" area: " + c.getDouble(5) + " habilitado: "+c.getInt(4)
            +" Number: "+c.getString(2)+" Tipo: "+ c.getString(3));
            listOwner.add(comu);

        }

        return listOwner;
    }





}
