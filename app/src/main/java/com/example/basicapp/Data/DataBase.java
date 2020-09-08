package com.example.basicapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.basicapp.Model.Content;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private Context context;
    private final String TAG = "DBmanager";
    public static final String DATABASE_NAME = "content_list";
    private static final String TABLE_NAME = "content";
    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String MONEY = "money";
    private static int Version = 2;
    private String SQLite = "CREATE TABLE " + TABLE_NAME + " (" + ID + " integer primary key, " +
            CONTENT + " TEXT, " +
            MONEY + " TEXT)";


    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, Version);
        this.context = context;
        Log.d(TAG, "DBmanager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLite);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpdate");
    }

    public void addContent(ContentData data) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,data.getmID());
        values.put(CONTENT, data.getmContent());
        values.put(MONEY, data.getmMoney());
        database.insert(TABLE_NAME, null, values);
        database.close();
        Log.d(TAG, "addContent Successfully");
    }

    public ArrayList<ContentData> getdata() {
        ArrayList<ContentData> Listcontent = new ArrayList<ContentData>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ContentData contentData = new ContentData();
                contentData.setmID(cursor.getInt(0));
                contentData.setmContent(cursor.getString(1));
                contentData.setmMoney(cursor.getString(2));
                Listcontent.add(contentData);
            } while (cursor.moveToNext());
        }
        db.close();
        return Listcontent;
    }


    public void updateNote(String content, String money, int ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("Content", content);
        values.put("Money", money);
        //updating row
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}
