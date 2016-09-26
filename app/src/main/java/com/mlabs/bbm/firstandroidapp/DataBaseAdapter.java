package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;
import java.util.regex.Pattern;

public class DataBaseAdapter extends SQLiteOpenHelper {
    private static final String TAG = DataBaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "uname";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_CREATED_AT = "created_at";

    public DataBaseAdapter(Context _context) {
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_EMAIL + " TEXT UNIQUE ,"
                + KEY_PASSWORD + " TEXT ,"
                + KEY_CREATED_AT + " TEXT ,"
                + KEY_USERNAME + " TEXT UNIQUE ,"
                + KEY_FIRSTNAME + " TEXT ,"
                + KEY_LASTNAME + " TEXT "+ ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database tables created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);

        onCreate(db);
    }

    public void registerUser(String email, String password, String created_at, String uname, String firstname, String lastname) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CREATED_AT, created_at);
        values.put(KEY_USERNAME, uname);
        values.put(KEY_FIRSTNAME, firstname);
        values.put(KEY_LASTNAME, lastname);


        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "Successfully Added User:" + id);
    }

    public boolean validateUser(String password, String username) {
        HashMap <String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT " + KEY_EMAIL + ", " + KEY_USERNAME + ", " + KEY_PASSWORD + " FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + username + "\" OR " + KEY_USERNAME + "=\"" + username + "\"  AND " + KEY_PASSWORD + "=\"" + password + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        boolean result = false;



        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(KEY_EMAIL, cursor.getString(0));
            user.put(KEY_PASSWORD, cursor.getString(1));
            user.put(KEY_USERNAME, cursor.getString(2));
//            user.put(KEY_FIRSTNAME, cursor.getString(3));
  //          user.put(KEY_LASTNAME, cursor.getString(4));
            //user.put("created_at", cursor.getString(3));
            result = true;
        }
        else
        {
            result = false;
        }
        cursor.close();
        db.close();
        return  result;
/**
 Log.d(TAG, "Fetching user from Sqlite:" + user.toString());
 Log.d(TAG, "Pw:" + user.get(password));
 if (password.equals(user.get(password))){
 Log.d(TAG, "Password was validated");
 return true;
 }
 else {
 Log.d(TAG, "Password Mismatch");
 return false;
 }
 **/
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user record from sqlite.");
    }



}
