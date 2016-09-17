package com.mlabs.bbm.firstandroidapp;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.ConnectException;
import java.sql.SQLClientInfoException;
import java.util.HashMap;
import java.util.SimpleTimeZone;

/**
 * Created by androidstudio on 17/09/16.
 */
public class DBHelp extends SQLiteOpenHelper {

    private static final String TAG = DBHelp.class.getSimpleName();

    private static String DATABASE_NAME = "Users.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "Users";

    private static final String KEY_ID ="id";
    private static final String KEY_USERNAME ="username";
    private static final String KEY_PASSWORD ="password";
    private static final String KEY_CREATED_AT ="created_at";


    public DBHelp(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB){
        String CREATE_USER_TABLE ="CREATE TABLE " +  TABLE_USER  + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT UNIQUE,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database Tables Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);

        onCreate(db);
    }

    public void registerUser(String username, String password, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CREATED_AT, created_at);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "Successfully Added user: " + id;

    }


    public boolean validateUser(String userName, String password){

        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USERNAME + "=" + userName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            user.put("username", cursor.getString(1));
            user.put("password", cursor.getString(2));
            user.put("created_at", cursor.getString(3));
        }
        cursor.close();

        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        if(password.equals(user.get(password))){
            Log.d(TAG, "Password was validated");
            return true;
        }
        else {
            Log.d(TAG, "Password mismatch ");
            return false;
        }
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user records from Sqlite");
    }

}
