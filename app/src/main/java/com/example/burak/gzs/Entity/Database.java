package com.example.burak.gzs.Entity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burak on 17.12.2017.
 */

public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "veriler";
    public static final int DATABASE_VERSİON = 1;
    public static final String DATABASE_TABLE = "veri";



    public final String ROW_ID = "id";
    public final String ROW_sicaklik = "sicaklik";
    public final String ROW_nem = "nem";
    public final String ROW_gaz = "gaz";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSİON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DATABASE_TABLE + "("+ROW_ID +" INTEGER PRIMARY KEY, "+
                ROW_sicaklik+" TEXT NOT NULL, "+
                ROW_nem+" TEXT NOT NULL,"+
                ROW_gaz+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void veriEkle(String sicaklik, String nem, String gaz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_sicaklik, sicaklik);
        cv.put(ROW_nem, nem);
        cv.put(ROW_gaz, gaz);
        db.insert(DATABASE_TABLE, null, cv);
        db.close();
    }

    public List<String> verileriListele() {

        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_sicaklik, ROW_nem, ROW_gaz};
        Cursor cursor = db.query(DATABASE_TABLE, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()){
            veriler.add(cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3));
        }

        return veriler;
    }

















}
