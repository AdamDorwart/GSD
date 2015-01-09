package com.epistemik.gsd.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adam on 1/8/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GSD.db";

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + InboxDataSource.TABLE_NAME + "(" +
        InboxDataSource.COLUMN_POS_ID + " INTEGER PRIMARY KEY, " +
        InboxDataSource.COLUMN_TITLE + " TEXT NOT NULL, " +
        InboxDataSource.COLUMN_DETAIL + " TEXT);";
    public static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + InboxDataSource.TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Current upgrade policy disposes database. When an update is required process must be made
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
