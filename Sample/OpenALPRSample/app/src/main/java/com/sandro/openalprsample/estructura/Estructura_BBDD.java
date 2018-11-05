package com.sandro.openalprsample.estructura;

public class Estructura_BBDD {

    public static final String TABLA_COMUNIDAD = "COMUNITY";

    public static final String COLUMNA_COMUNIDAD_ID = "COM_ID";
    public static final String COLUMNA_COMUNIDAD_TIPOCOMUNIDDA = "COM_TYPE";
    public static final String COLUMNA_COMUNIDAD_NOMBRE = "COM_NAME";


    public static final String TABLA_PROPIETARIO = "OWNER";

    public static final String COLUMNA_PROPIETARIO_ID = "OWN_ID";
    public static final String COLUMNA_PROPIETARIO_NOMBRE = "OWN_NAME";
    public static final String COLUMNA_PROPIETARIO_APELLIDO = "OWN_LASTNAME";
    public static final String COLUMNA_PROPIETARIO_NUMEROIDENTIDAD = "OWN_IDENTIFICATION_NUMBER";
    public static final String COLUMNA_PROPIETARIO_TIPOIDENTIDAD =  "OWN_TYPE_IDENTIFICATION";
    public static final String COLUMNA_PROPIETARIO_IDCOMUNIDAD =  "COM_ID";



    public static final String TABLA_PROPIEDAD = "OWNERSHIP";

    public static final String COLUMNA_PROPIEDAD_ID = "OSH_ID";
    public static final String COLUMNA_PROPIEDAD_IDPROPIETARIO = "OWN_ID";
    public static final String COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD = "OSH_NUMBER";
    public static final String COLUMNA_PROPIEDAD_TIPOPROPIETARIO = "OSH_TYPE";
    public static final String COLUMNA_PROPIEDAD_HABITADO = "OSH_INHABITED";
    public static final String COLUMNA_PROPIEDAD_AREA = "OSH_AREA";





    public static final String TABLA_VEHICULO = "VEHICLE";

    public static final String COLUMNA_VEHICULO_ID = "VEH_ID";
    public static final String COLUMNA_VEHICULO_IDPROPIETARIO = "OWN_ID";
    public static final String COLUMNA_VEHICULO_MARCA = "VEH_MAKE";
    public static final String COLUMNA_VEHICULO_MODELO = "VEH_MODEL";
    public static final String COLUMNA_VEHICULO_COLOR = "VEH_COLOUR";
    public static final String COLUMNA_VEHICULO_AÑO = "VEH_YEAR";
    public static final String COLUMNA_VEHICULO_PLATENUMBER = "VEH_LICENCEPLATE";


    public static final String TABLA_HISTORICO = "ACCESSHISTORY";

    public static final String COLUMNA_HISTORICO_ID = "HIS_ID";
    public static final String COLUMNA_HISTORICO_IDPROPIETARIO = "OWN_ID";
    public static final String COLUMNA_HISTORICO_IDCOMUNIDAD = "COM_ID";
    public static final String COLUMNA_HISTORICO_FECHA = "HIS_DATE";
    public static final String COLUMNA_HISTORICO_HORA = "HIS_HOUR";
    public static final String COLUMNA_HISTORICO_TIPOACCESO = "HIS_TYPEACCESS";
    public static final String COLUMNA_HISTORICO_TIPOSEGURIDDA = "HIS_TYPESECURITY";
    public static final String COLUMNA_HISTORICO_FOTO = "HIS_PHOTO";



    public static final String CREATE_TABLA_COMUNIDAD = " CREATE TABLE " +

              Estructura_BBDD.TABLA_COMUNIDAD + "("
            + Estructura_BBDD.COLUMNA_COMUNIDAD_ID  +" INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + Estructura_BBDD.COLUMNA_COMUNIDAD_NOMBRE  + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_COMUNIDAD_TIPOCOMUNIDDA + " VARCHAR(30) NOT NULL )";

    public static final String CREATE_TABLA_PROPIETARIO = "CREATE TABLE " +
            Estructura_BBDD.TABLA_PROPIETARIO + "("
            + Estructura_BBDD.COLUMNA_PROPIETARIO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIETARIO_NOMBRE + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIETARIO_APELLIDO + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD + " VARCHAR(15) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD + " VARCHAR(3) NOT NULL ,"
            +"UNIQUE("+ Estructura_BBDD.COLUMNA_PROPIETARIO_NUMEROIDENTIDAD  +")"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD
            + ") REFERENCES "+ Estructura_BBDD.TABLA_COMUNIDAD + " ("+ COLUMNA_COMUNIDAD_ID +") )";

    public static final String CREATE_TABLA_PROPIEDAD = "CREATE TABLE " +
            Estructura_BBDD.TABLA_PROPIEDAD + "("
            + Estructura_BBDD.COLUMNA_PROPIEDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD+ " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO+ " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO+ " INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_AREA+ " REAL NOT NULL,"
            +"UNIQUE("+ Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD+")"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO
            + ") REFERENCES "+ Estructura_BBDD.TABLA_PROPIETARIO + "("+ COLUMNA_PROPIETARIO_ID +") )";


    public static final String CREATE_TABLA_VEHICULO ="CREATE TABLE " +
            Estructura_BBDD.TABLA_VEHICULO + "("
            + Estructura_BBDD.COLUMNA_VEHICULO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_MARCA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_MODELO +  " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_COLOR +  " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_AÑO + " INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER +" VARCHAR(10) NOT NULL,"
            +"UNIQUE("+Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER+")"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO
            + ") REFERENCES " + Estructura_BBDD.TABLA_PROPIETARIO + "("+ COLUMNA_PROPIETARIO_ID +") )";

    public static final String CREATE_TABLA_HISTORICO = "CREATE TABLE " +
            Estructura_BBDD.TABLA_HISTORICO + "("
            + Estructura_BBDD.COLUMNA_HISTORICO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_FECHA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_HORA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO +" VARCHAR(20) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_FOTO + " BLOB ,"
            + Estructura_BBDD.COLUMNA_HISTORICO_IDCOMUNIDAD +" Integer ,"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO
            + ") REFERENCES " + Estructura_BBDD.TABLA_PROPIETARIO + "("+ COLUMNA_PROPIETARIO_ID +"),"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_HISTORICO_IDCOMUNIDAD
            + ") REFERENCES "+ Estructura_BBDD.TABLA_COMUNIDAD + " ("+ COLUMNA_COMUNIDAD_ID +") )";






    public static final String DELETE_TABLA_COMUNIDAD = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_COMUNIDAD;

    public static final String DELETE_TABLA_PROPIETARIO = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_PROPIETARIO;

    public static final String DELETE_TABLA_PROPIEDAD = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_PROPIEDAD;

    public static final String DELETE_TABLA_VEHICLE = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_VEHICULO;


}
