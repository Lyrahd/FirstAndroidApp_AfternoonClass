package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class DataBaseAdapter extends SQLiteOpenHelper {


    private static final String TAG=DataBaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "registered_users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_UNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FNAME = "firstName";
    private static final String KEY_LNAME = "lastName";

    public DataBaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UNAME + " TEXT UNIQUE," + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT," + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String registerUser(String fname, String lname, String uname, String email, String password) {
        SQLiteDatabase wdb = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, uname);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_FNAME, fname);
        values.put(KEY_LNAME, lname);

        long id = wdb.insert(TABLE_USER, null, values);
        wdb.close();

        Log.d(TAG, "Successfully added user: " + id);
        Log.d(TAG, "Successfully added user: " + email);
        Log.d(TAG, "Successfully added user: " + password);
        return "User successfully added";
        }



    public boolean validateuname(String uname){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_UNAME + "=\"" + uname + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }
    public boolean validateemail(String email){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + email + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }

    public boolean validateUser(String unameOrMail, String pwd){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + unameOrMail + "\""+" OR " +  KEY_UNAME + "=\"" + unameOrMail + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("uname",cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("password", cursor.getString(3));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());
        if (((unameOrMail.equals(user.get("email"))) || (unameOrMail.equals(user.get("uname")))) &&(pwd.equals(user.get("password")))) {
            return true;
        } else {
            Log.d(TAG, "does not exist");
            return false;
        }
    }

}
