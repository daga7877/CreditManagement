package com.example.mss2015.creditmanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String db_name="user.db";
    public static final int db_version=1;
    public static final String tbl_name="user";
    public static final String tbl_name1="Transferrecord";

    public database(Context context) {

        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q ="create table "+tbl_name +"(name text primary key ,email text ,credit double)";
        String q1 ="create table "+tbl_name1 +"(namef text ,namet text,amount double)";
        db.execSQL(q);
        db.execSQL(q1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
