package com.dam.MediTrackYou.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String usuario = "CREATE TABLE user ( " +
            "id_user INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "dni TEXT," +
            "age INTEGER," +
            "name TEXT," +
            "lastname TEXT," +
            "birth_date TEXT," +
            "gender TEXT," +
            "nationality TEXT," +
            "email TEXT," +
            "phone TEXT," +
            "password TEXT)";

    public static final String dbName = "meditrackyou.sqlite";
    public static final int dbversion = 1;

    public MyOpenHelper(Context context) {
        super(context, dbName, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
