package com.example.nhom13.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class EventHelper {
    private final DBHelper dbHelper;

    public EventHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Cursor getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM event";
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public int insert(String name, String date, String time, String repeat, int setting, int important){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("repeat", repeat);
        cv.put("setting", setting);
        cv.put("important", important);

        long result = db.insert("event", null, cv);
        if(result == -1){
            return 0;
        }
        return 1;
    }

    public int update(int id, String name, String date, String time, String repeat, int setting, int important){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("repeat", repeat);
        cv.put("setting", setting);
        cv.put("important", important);
        long result = db.update("event", cv, " id = ? ", new String[] {String.valueOf(id)});
        if(result == -1){
            return 0;
        }
        return 1;
    }

    public Cursor getByDate(String date){
        Cursor cursor = null;
        String sql = "SELECT * FROM event WHERE date = '" + date + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db !=null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public int delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete("event", "id = ?", new String[]{String.valueOf(id)});
        if(result == -1){
            return 0;
        }
        return 1;
    }
}
