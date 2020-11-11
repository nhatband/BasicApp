package com.example.basicapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private Context context;
    private final String TAG = "DBmanager";
    public static final String DATABASE_NAME = "list_content";
    private static final String TABLE_NAME = "works";
    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String MONEY = "money";
    private static int Version = 5;
    private String SQLite = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY , " +
            CONTENT + " TEXT NOT NULL, " +
            MONEY + " TEXT NOT NULL)";


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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    public void addContent(ContentData data) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(ID, data.getmID());
        values.put(CONTENT, data.getmContent());
        values.put(MONEY, data.getmMoney());
        database.insert(TABLE_NAME, null, values);
        database.close();
        Log.d(TAG, "addContent Successfully");
    }

    public ContentData getSdtudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,
                        CONTENT, MONEY}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME +" WHERE id = '1'",null);
        if (cursor != null)
            cursor.moveToFirst();

        ContentData student = new ContentData(cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return student;
    }

    public ArrayList<ContentData> getAlldata() {
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


    public int updateNote(ContentData contentData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTENT, contentData.getmContent());
        values.put(MONEY, contentData.getmMoney());
        return db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(contentData.getmID())});
    }

    public int deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(id)});
    }
//    public int getStudentsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
}
