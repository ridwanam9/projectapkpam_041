package com.example.uaspam_041.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DBLightNovel extends SQLiteOpenHelper{

    public static final String DBNAME = "books.db";

    public DBLightNovel(Context context) {
        super(context, "books.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table book( id_novel Integer primary key, judul text unique, penulis text, sinopsis text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bookstore, int i, int i1) {
        bookstore.execSQL("drop table if exists book");
        onCreate(bookstore);
    }

    public void insertBook(HashMap<String,String> queryValues){

        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("judul", queryValues.get("judul"));
        nilai.put("penulis", queryValues.get("penulis"));
        nilai.put("sinopsis", queryValues.get("sinopsis"));
        basisdata.insert("book", null, nilai);
        basisdata.close();
    }

    public ArrayList<HashMap<String,String>> getAllNovel(){
        ArrayList<HashMap<String,String>> daftarLightNovel;
        daftarLightNovel = new ArrayList<HashMap<String ,String>>();
        String selectQuery = "Select * from book";
        SQLiteDatabase bookstore = this.getReadableDatabase();
        Cursor cursor = bookstore.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> map = new HashMap<>();
                map.put("id_novel", cursor.getString(0));
                map.put("judul", cursor.getString(1));
                map.put("penulis", cursor.getString(2));
                map.put("sinopsis", cursor.getString(3));
                daftarLightNovel.add(map);
            }while (cursor.moveToNext());
        }
        bookstore.close();
        return daftarLightNovel;
    }

    public void UpdateData(HashMap<String,String> queryValues){
        SQLiteDatabase bookstore = getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("judul", queryValues.get("judul"));
        nilai.put("penulis", queryValues.get("penulis"));
        nilai.put("sinopsis", queryValues.get("sinopsis"));
        bookstore.update("book", nilai, "id_novel=?", new String[]{queryValues.get("id_novel")});
        bookstore.close();
    }

    public void deleteData(HashMap<String,String> queryValue){
        SQLiteDatabase bookstore = getWritableDatabase();
        bookstore.delete("book", "id_novel=?", new String[]{queryValue.get("id_novel")});
        bookstore.close();
    }

}
