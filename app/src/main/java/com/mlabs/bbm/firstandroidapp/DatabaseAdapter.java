package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DatabaseAdapter extends SQLiteOpenHelper{
    private static final String TAG = DatabaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATED_AT = "created_at";


    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME,  null,    DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_EMAIL + " TEXT UNIQUE, " +
                KEY_PASSWORD + " TEXT, " +
                KEY_CREATED_AT + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
        Log.d(TAG,"Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void registeruser(String email, String password, String created_at){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_CREATED_AT,created_at);

        long id  = db.insert(TABLE_USER,null, values);
        db.close();
        Log.d(TAG, "User added successfully!");
    }

    public boolean validateUser(String email, String password){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM "+TABLE_USER+" WHERE " +KEY_EMAIL+ "='" + email+"' AND " + KEY_PASSWORD + "="+password;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if (cursor.getCount()>0){
            user.put("email",cursor.getString(1));
            user.put("password",cursor.getString(2));
            user.put("created_at",cursor.getString(3));
            Log.d(TAG,"Fetching user from Sqlite: "+user.toString());
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public  void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user from sqlite");
    }
}