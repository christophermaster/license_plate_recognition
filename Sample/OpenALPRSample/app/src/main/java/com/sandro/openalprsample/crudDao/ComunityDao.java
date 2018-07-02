package com.sandro.openalprsample.crudDao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.sandro.openalprsample.entity.Comunity;
import com.sandro.openalprsample.estructura.Estructura_BBDD;

import java.util.ArrayList;


public class ComunityDao {

    private static ArrayList<Comunity> listComunity;


    public static Long createComunity(EditText nameComunity, EditText typeComunity, SQLiteDatabase db){



        ContentValues values = new ContentValues();

        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE, nameComunity.getText().toString());
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA, typeComunity.getText().toString());


        Long newRowId = db.insert(Estructura_BBDD.TABLA_COMUNIDAD,null,values);

        return newRowId;
    }


    public static Integer updateComunity(EditText nameComunity, EditText typeComunity, EditText id, SQLiteDatabase db){



        ContentValues values = new ContentValues();
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE,nameComunity.getText().toString());
        values.put(Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA,typeComunity.getText().toString());

        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + "= ?";
        String[] selectionArgs = {id.getText().toString()};

        int count = db.update(
                Estructura_BBDD.TABLA_COMUNIDAD,
                values,
                selection,
                selectionArgs
        );


        return count;
    }


    public static Integer deleteComunity(EditText id, SQLiteDatabase db){

        String selection = Estructura_BBDD.COLUMNA_COMUNIDAD_ID + " = ?";
        String[] selectionArgs = {id.getText().toString()};

        int count = db.delete(Estructura_BBDD.TABLA_COMUNIDAD,selection,selectionArgs);
        return count;


    }


    public static ArrayList<Comunity> listComunity(SQLiteDatabase db){

        Comunity comu = null;
        listComunity = new ArrayList<Comunity>();

        Cursor c = db.rawQuery("SELECT * FROM " + Estructura_BBDD.TABLA_COMUNIDAD,null);

        while (c.moveToNext()){

            comu  = new Comunity();

            comu.setIdComunity(c.getInt(0));
            comu.setNameComunity(c.getString(1));
            comu.setTypeComunity(c.getString(2));

            listComunity.add(comu);

        }

        return listComunity;
    }


}
