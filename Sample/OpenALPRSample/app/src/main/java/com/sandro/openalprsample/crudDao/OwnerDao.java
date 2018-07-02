package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sandro.openalprsample.entity.OwnerEntity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;

public class OwnerDao {

    private static ArrayList<OwnerEntity> listOwner;


    public static Long createOwner(String nameOwner, String surnameOwner ,Integer idComunity,
                                      String numberIdentity, String typeIdentity,SQLiteDatabase db){

        System.out.print(nameOwner+surnameOwner+idComunity+numberIdentity+typeIdentity);
        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,idComunity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE, nameOwner);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO, surnameOwner);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,numberIdentity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD,typeIdentity);

        Long newRowId = db.insert(Estructura_BBDD.TABLA_PROPIETARIO,null,values);

        return newRowId;

    }



    public static Integer updateOwner(String id, String nameOwner, String surnameOwner ,Integer idComunity,
                                      String numberIdentity, String typeIdentity,SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD,idComunity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE, nameOwner);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO, surnameOwner);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD,numberIdentity);
        values.put(Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD,typeIdentity);

        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + "= ?";
        String[] selectionArgs = {id};

        int count = db.update(
                Estructura_BBDD.TABLA_PROPIETARIO,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteOwner(String id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_PROPIETARIO_ID + " = ?";
        String[] selectionArgs = {id};

        int count = db.delete(Estructura_BBDD.TABLA_PROPIETARIO,selection,selectionArgs);
        return count;


    }


    public static ArrayList<OwnerEntity> listOwner(SQLiteDatabase db){

        OwnerEntity comu = null;
        listOwner = new ArrayList<OwnerEntity>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_PROPIETARIO,null);

        while (c.moveToNext()){

            comu  = new OwnerEntity();

            comu.setIdOwner(c.getInt(0));
            comu.setIdcommunity(c.getInt(1));
            comu.setNameOwner(c.getString(2));
            comu.setSurnameOwner(c.getString(3));
            comu.setNumberIdentity(c.getString(4));
            comu.setTypeIdentity(c.getString(5));

            listOwner.add(comu);

        }

        return listOwner;
    }




}
