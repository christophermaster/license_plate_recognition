package com.sandro.openalprsample.estructura;

public class Estructura_BBDD {

    public static final String TABLA_COMUNIDAD = "COMUNIDAD";

    public static final String COLUMNA_COMUNIDAD_ID = "COM_ID";
    public static final String COLUMNA_COMUNIDAD_TIPOCOMUNIDDA = "COM_TIPOCOMUNIDAD";
    public static final String COLUMNA_COMUNIDAD_NOMBRE = "COM_NOMBRE";


    public static final String TABLA_PROPIETARIO = "PROPIETARIO";

    public static final String COLUMNA_PROPIETARIO_ID = "PRO_ID";
    public static final String COLUMNA_PROPIETARIO_NOMBRE = "PRO_NOMBRE";
    public static final String COLUMNA_PROPIETARIO_APELLIDO = "PRO_APELLIDO";
    public static final String COLUMNA_PROPIETARIO_NUMEROIDENTIDAD = "PRO_IDENTIDAD";
    public static final String COLUMNA_PROPIETARIO_TIPOIDENTIDAD =  "PRO_TIPOIDENTIDAD";
    public static final String COLUMNA_PROPIETARIO_IDCOMUNIDAD =  "COM_ID";



    public static final String TABLA_PROPIEDAD = "PROPIEDAD";

    public static final String COLUMNA_PROPIEDAD_ID = "PAD_ID";
    public static final String COLUMNA_PROPIEDAD_IDPROPIETARIO = "PRO_ID";
    public static final String COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD = "PAD_NUMEROPROPIEDAD";
    public static final String COLUMNA_PROPIEDAD_TIPOPROPIETARIO = "PAD_TIPOPROPIEDAD";
    public static final String COLUMNA_PROPIEDAD_HABITADO = "PAD_HABITADO";
    public static final String COLUMNA_PROPIEDAD_AREA = "PAD_AREA";





    public static final String TABLA_VEHICULO = "VEHICULO";

    public static final String COLUMNA_VEHICULO_ID = "VEH_ID";
    public static final String COLUMNA_VEHICULO_IDPROPIETARIO = "PRO_ID";
    public static final String COLUMNA_VEHICULO_MARCA = "VEH_MARCA";
    public static final String COLUMNA_VEHICULO_MODELO = "VEH_MODELO";
    public static final String COLUMNA_VEHICULO_COLOR = "VEH_COLOR";
    public static final String COLUMNA_VEHICULO_AÑO = "VEH_AÑO";
    public static final String COLUMNA_VEHICULO_PLATENUMBER = "VEH_NUMEROPLACA";


    public static final String TABLA_HISTORICO = "HISTORICOACCESO";

    public static final String COLUMNA_HISTORICO_ID = "HIS_ID";
    public static final String COLUMNA_HISTORICO_IDPROPIETARIO = "PRO_ID";
    public static final String COLUMNA_HISTORICO_FECHA = "HIS_FECHA";
    public static final String COLUMNA_HISTORICO_HORA = "HIS_HORA";
    public static final String COLUMNA_HISTORICO_TIPOACCESO = "HIS_TIPOACESSO";
    public static final String COLUMNA_HISTORICO_TIPOSEGURIDDA = "HIS_TIPOSEGURIDAD";
    public static final String COLUMNA_HISTORICO_FOTO = "HIS_FOTO";

    public static final String COMA = ",";




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
            +Estructura_BBDD.COLUMNA_PROPIETARIO_TIPOIDENTIDAD + " VARCHAR(3) NOT NULL ,"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_PROPIETARIO_IDCOMUNIDAD
            + ") REFERENCES bus("+ COLUMNA_COMUNIDAD_ID +") )";

    public static final String CREATE_TABLA_PROPIEDAD = "CREATE TABLE " +
            Estructura_BBDD.TABLA_PROPIEDAD + "("
            + Estructura_BBDD.COLUMNA_PROPIEDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_NUMERODEPROPIEDAD+ " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_TIPOPROPIETARIO+ " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_HABITADO+ " Bit NOT NULL,"
            + Estructura_BBDD.COLUMNA_PROPIEDAD_AREA+ " REAL NOT NULL,"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_PROPIEDAD_IDPROPIETARIO
            + ") REFERENCES bus("+ COLUMNA_PROPIETARIO_ID +") )";


    public static final String CREATE_TABLA_VEHICULO ="CREATE TABLE " +
            Estructura_BBDD.TABLA_VEHICULO + "("
            + Estructura_BBDD.COLUMNA_VEHICULO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_MARCA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_MODELO +  " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_COLOR +  " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_AÑO + " INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_VEHICULO_PLATENUMBER +" VARCHAR(10) NOT NULL,"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_VEHICULO_IDPROPIETARIO
            + ") REFERENCES bus("+ COLUMNA_PROPIETARIO_ID +") )";

    public static final String CREATE_TABLA_HISTORICO = "CREATE TABLE " +
            Estructura_BBDD.TABLA_HISTORICO + "("
            + Estructura_BBDD.COLUMNA_HISTORICO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO +" INTEGER NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_FECHA
            + Estructura_BBDD.COLUMNA_HISTORICO_HORA
            + Estructura_BBDD.COLUMNA_HISTORICO_TIPOACCESO +" VARCHAR(20) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_TIPOSEGURIDDA + " VARCHAR(30) NOT NULL,"
            + Estructura_BBDD.COLUMNA_HISTORICO_FOTO + "BLOB NOT NULL,"
            +"  FOREIGN KEY("+ Estructura_BBDD.COLUMNA_HISTORICO_IDPROPIETARIO
            + ") REFERENCES bus("+ COLUMNA_PROPIETARIO_ID +") )";






    public static final String DELETE_TABLA_COMUNIDAD = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_COMUNIDAD;

    public static final String DELETE_TABLA_PROPIETARIO = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_PROPIETARIO;

    public static final String DELETE_TABLA_PROPIEDAD = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_PROPIEDAD;

    public static final String DELETE_TABLA_VEHICLE = "DROP TABLE IF EXISTS "
            + Estructura_BBDD.TABLA_VEHICULO;



    private Estructura_BBDD(){}

}
