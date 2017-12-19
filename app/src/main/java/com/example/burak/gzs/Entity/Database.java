package com.example.burak.gzs.Entity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burak on 17.12.2017.
 */

public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "veriler";
    public static final int DATABASE_VERSİON = 2;
    public static final String DATABASE_TABLE = "veri";
    public static final String DATABASE_TABLE1 = "user";
    public static final String DATABASE_TABLE2 = "sinirTablo";


    public final String ROW_ID = "id";
    public final String ROW_sicaklik = "sicaklik";
    public final String ROW_nem = "nem";
    public final String ROW_gaz = "gaz";

    public final String ROW_ID1 = "id";
    public final String ROW_username = "username";
    public final String ROW_password = "password";

    public final String ROW_ID2 = "id";
    public final String ROW_sicaklik1 = "sicaklik1";
    public final String ROW_nem1 = "nem1";
    public final String ROW_gaz1 = "gaz1";




    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSİON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DATABASE_TABLE + "("+ROW_ID +" INTEGER PRIMARY KEY, "+
                ROW_sicaklik+" TEXT NOT NULL, "+
                ROW_nem+" TEXT NOT NULL,"+
                ROW_gaz+" TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+ DATABASE_TABLE1 + "("+ROW_ID1 +" INTEGER PRIMARY KEY, "+
                ROW_username+" TEXT NOT NULL, "+
                ROW_password+" TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+ DATABASE_TABLE2 + "("+ROW_ID2 +" INTEGER PRIMARY KEY, "+
                ROW_sicaklik1+" TEXT NOT NULL, "+
                ROW_nem1+" TEXT NOT NULL,"+
                ROW_gaz1+" TEXT NOT NULL)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);

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

    public void veriEkle2(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_username, username);
        cv.put(ROW_password, password);
        db.insert(DATABASE_TABLE1, null, cv);
        db.close();
        Log.d("Kayıt Barasrili", "Kayit olduk");

    }

    public void veriEkle3(String sicaklik, String nem, String gaz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_sicaklik1, sicaklik);
        cv.put(ROW_nem1, nem);
        cv.put(ROW_gaz1, gaz);
        db.insert(DATABASE_TABLE2, null, cv);
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

    public List<String> verileriListele2() {

        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_sicaklik1, ROW_nem1, ROW_gaz1};
        Cursor cursor = db.query(DATABASE_TABLE2, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()){
            veriler.add("Nem->" + cursor.getString(1) + "  Sicaklik-> " + cursor.getString(2) + " Gaz-> " + cursor.getString(3));
        }

        return veriler;
    }


    public Boolean loginOl(String usr, String pwd) {
        Boolean var = Boolean.FALSE;
        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_username, ROW_password};
        Cursor cursor = db.query(DATABASE_TABLE1, sutunlar, null, null, null, null, null);
        while (cursor.moveToNext()){
            veriler.add(cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2));
            if (cursor.getString(1).equals(usr) && cursor.getString(2).equals(pwd)){
                var = Boolean.TRUE;
            }
        }

        return var;
    }

















}
