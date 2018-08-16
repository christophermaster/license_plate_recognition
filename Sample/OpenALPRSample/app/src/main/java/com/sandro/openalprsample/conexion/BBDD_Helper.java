package com.sandro.openalprsample.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sandro.openalprsample.estructura.Estructura_BBDD;


public class BBDD_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1 ;

    //Nombre de la base da datos
    public static final String DATABASE_NAME = "ReconocimientoPlaca.db";

    // Se crea la conexión
    public BBDD_Helper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    //Si la base de datos no Existe se crea
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Estructura_BBDD.CREATE_TABLA_COMUNIDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIETARIO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIEDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_VEHICULO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_HISTORICO);

    }

    //Si Existe se actualiza
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Estructura_BBDD.CREATE_TABLA_COMUNIDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIETARIO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_PROPIEDAD);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_VEHICULO);
        db.execSQL(Estructura_BBDD.CREATE_TABLA_HISTORICO);
        onCreate(db);

    }

    //Cambio de versiones
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){

        onUpgrade(db,oldVersion,newVersion);

    }
}
