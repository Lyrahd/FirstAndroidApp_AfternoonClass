package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashMap;

/**
 * Created by androidstudio on 17/09/16.
 */
public class databaseAdapter extends SQLiteOpenHelper {
    private static final String TAG = databaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String KEY_ID ="id";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_PASSWORD ="password";
    private static final String KEY_CREATED_AT ="created_at";

    public databaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT UNIQUE,"
        + KEY_PASSWORD + " TEXT," + KEY_CREATED_AT + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database tables created");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void registerUser(String email, String password, String created_at){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CREATED_AT, created_at);
        long id = db.insert(TABLE_USER, null, values);
        db.close();
        Log.d(TAG, "Successfully Registered: " + id);
    }

    public boolean validateUser(String username, String password){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT email, password FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + username +"\" AND " + KEY_PASSWORD + "=\"" + password + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean res = false;
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            user.put(KEY_EMAIL, cursor.getString(0));
            user.put(KEY_PASSWORD, cursor.getString(1));
            res = true;
            //user.put("created_at", cursor.getString(3));
        }
        else{
            res = false;
        }
        cursor.close();
        db.close();
        return res;
        /**Log.d(TAG, "Fetching user from SqLite: " + user.toString());

        if (password.equals(user.get(password))){
            Log.d(TAG, "Password was validated");
            return true;
        }
        else{
            Log.d(TAG, "Password mismatch");
            return false;
        }
         **/
    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user records from SQLite");
    }



}