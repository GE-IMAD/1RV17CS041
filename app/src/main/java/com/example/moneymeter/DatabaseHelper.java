package com.example.moneymeter;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "MoneyRecord";

    //database version
    public static final int DATABASE_VERSION = 1;

    //table name
    public static final String TABLE_NAME = "TRANSACTION_LIST";

    //table column name
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_Date = "Date";
    public static final String COLUMN_Descrip = "Description";
    public static final String COLUMN_Amt = "Amount";
    public static final String COLUMN_Type = "Type";
    public static final String COLUMN_Bal = "Balance";

    //Query to create Table
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_Date + " TEXT," +
            COLUMN_Descrip + " TEXT,"+
            COLUMN_Amt + " REAL, "+
            COLUMN_Type + " TEXT, "+
            COLUMN_Bal + " REAL ) ";

    //Query to delete table
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //implementing interface in SQLiteOpenHelper to create database and execute CREATE_TABLE string query
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //implementing interface in SQLiteOpenHelper to update database and execute CREATE_TABLE string query
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    /*
    //implementing interface in SQLiteOpenHelper to downgrade database and execute CREATE_TABLE string query
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }*/

}
