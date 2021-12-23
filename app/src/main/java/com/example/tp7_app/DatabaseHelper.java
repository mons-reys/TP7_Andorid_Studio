package com.example.tp7_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static  final String DATABASE_NAME = "my_db";
    private static  final int VERSION = 1;
    private static  final String TABLE_NAME = "Module";
    public  DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATETABLE = "create table " + TABLE_NAME +
                "(id INTEGER PRIMARY KEY, Module TEXT," +
                " Acronyme TEXT, VH TEXT, Departement TEXT )";
        db.execSQL(CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public  boolean addText(String module, String acronyme, String vh, String departement){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Module", module);
        contentValues.put("Acronyme", acronyme);
        contentValues.put("VH", vh);
        contentValues.put("Departement", departement);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    @SuppressLint("Range")
    public ArrayList getAllext(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +
                TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            arrayList.add(
                    cursor.getString(cursor.getColumnIndex("Module")) + " | " +
                    cursor.getString(cursor.getColumnIndex("Acronyme")) + " | " +
                    cursor.getString(cursor.getColumnIndex("VH")) + " | " +
                    cursor.getString(cursor.getColumnIndex("Departement")));
            cursor.moveToNext();

        }
        return  arrayList;
    }
}
