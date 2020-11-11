package com.example.mascotasrating.BD;

public class BD {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mascotas_rating.db";

    //Tabla mascotas
    public static final String TABLE_MASCOTA = "mascota";
    public static final String DROP_MASCOTA = "DROP TABLE IF EXISTS " + TABLE_MASCOTA;
    public static final String CREATE_MASCOTA = "CREATE TABLE " + TABLE_MASCOTA + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT," +
            "rating INT," +
            "foto INT," +
            "color INT)";

}
