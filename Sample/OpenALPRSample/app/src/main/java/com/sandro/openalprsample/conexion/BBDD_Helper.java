package com.sandro.openalprsample.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sandro.openalprsample.estructura.Estructura_BBDD;


public class BBDD_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1 ;
    public static final String DATABASE_NAME = "ReconocimientoPlaca.db";

    public BBDD_Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Estructura_BBDD.CREATE_TABLA_COMUNIDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIETARIO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIEDAD);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Estructura_BBDD.CREATE_TABLA_COMUNIDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIETARIO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIEDAD);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){

        onUpgrade(db,oldVersion,newVersion);

    }
}
