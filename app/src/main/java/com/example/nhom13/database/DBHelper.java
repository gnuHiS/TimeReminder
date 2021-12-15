package com.example.nhom13.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "timeReminder";
    private static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_event = "CREATE TABLE event (id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(200), " +
                "date VARCHAR(200), time VARCHAR(200), repeat VARCHAR(50), setting INTEGER, important INTEGER);";
        db.execSQL(sql_event);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
