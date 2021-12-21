package com.example.nhom13.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DiaryHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "timeReminder";
    private static final int VERSION = 1;

    public DiaryHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void CreatTable(){
        SQLiteDatabase db = getWritableDatabase();
        String sql_diary = "CREATE TABLE IF NOT EXISTS diary(id INTEGER PRIMARY KEY AUTOINCREMENT , title VARCHAR(200), " +
                "date VARCHAR(200), contentMain VARCHAR(200), content VARCHAR(200));";
        db.execSQL(sql_diary);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_diary = "CREATE TABLE IF NOT EXISTS diary(id INTEGER PRIMARY KEY AUTOINCREMENT , title VARCHAR(200), " +
                "date VARCHAR(200), contentMain VARCHAR(200), content VARCHAR(200));";
        db.execSQL(sql_diary);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAll(){
        System.out.println("abc");
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM diary";
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;

    }

    public boolean Insert(String title,String date, String contentMain, String content){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("date",date);
        cv.put("contentMain",contentMain);
        cv.put("content",content);

        long result = db.insert("diary",null, cv);
        if(result == -1){
            return false;
        }
        return true;
    }

    public boolean Update(){
        return false;
    }

    public boolean Delete(){
        return false;
    }
}
