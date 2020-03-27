package com.example.moneymeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase db;

    public DbManager(Context c) {
        context = c;
    }

    public DbManager open(){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String date, String descrip,Double amt,String type,Double bal) {

        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.COLUMN_Date, date);
        contentValue.put(DatabaseHelper.COLUMN_Descrip, descrip);
        contentValue.put(DatabaseHelper.COLUMN_Amt, amt);
        contentValue.put(DatabaseHelper.COLUMN_Type, type);
        contentValue.put(DatabaseHelper.COLUMN_Bal,bal);
        db.insert(DatabaseHelper.TABLE_NAME, null, contentValue);

    }
    /**/
    String[] columns = new String[] { DatabaseHelper.COLUMN_id, DatabaseHelper.COLUMN_Date, DatabaseHelper.COLUMN_Descrip,DatabaseHelper.COLUMN_Amt,DatabaseHelper.COLUMN_Type,DatabaseHelper.COLUMN_Bal};

    public Cursor fetch() {
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getAllColumns(){
        return columns;
    }

    DateFormat dateFormat = new SimpleDateFormat("-MM-yyyy");
    public Cursor fetchIncome(){
        Date date=new Date();
        String strDate = dateFormat.format(date);
        String dateLike="%"+strDate+"%";
        Cursor cursor=db.rawQuery("SELECT "+DatabaseHelper.COLUMN_Descrip+" , SUM("+DatabaseHelper.COLUMN_Amt+") as i FROM "+ DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COLUMN_Type+" = ?"+" AND "+DatabaseHelper.COLUMN_Date+" Like ? "+"GROUP BY "+DatabaseHelper.COLUMN_Descrip,new  String[] {"credit",dateLike});
        return cursor;
    }

    public Cursor fetchExpense(){
        Date date=new Date();
        String strDate = dateFormat.format(date);
        String dateLike="%"+strDate+"%";
        Cursor cursor=db.rawQuery("SELECT "+DatabaseHelper.COLUMN_Descrip+" , SUM("+DatabaseHelper.COLUMN_Amt+") as s FROM "+ DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COLUMN_Type+" = ?"+" AND "+DatabaseHelper.COLUMN_Date+" Like ? "+"GROUP BY "+DatabaseHelper.COLUMN_Descrip,new  String[] {"debit",dateLike});
        return cursor;
    }
}
