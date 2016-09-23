package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DataBaseAdapter extends SQLiteOpenHelper {


    private static final String TAG=DataBaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME="registered_users.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_USER="user";
    private static final String KEY_ID="id";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";

    public DataBaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE="CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String registerUser(String email, String password) {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + email + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("email", cursor.getString(1));
        }
        cursor.close();
        db.close();

        if (email.equals(user.get("email"))) {
            return "Email already in used.";
        } else {
            SQLiteDatabase wdb = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, email);
            values.put(KEY_PASSWORD, password);

            long id = wdb.insert(TABLE_USER, null, values);
            db.close();

            Log.d(TAG, "Successfully added user: " + id);
            Log.d(TAG, "Successfully added user: " + email);
            Log.d(TAG, "Successfully added user: " + password);
            return "User successfully added";
        }
    }

    public boolean validateUser(String email, String pwd){
        HashMap<String, String> user=new HashMap<String, String>();
        String selectQuery="SELECT * FROM "+TABLE_USER+" WHERE "+KEY_EMAIL+"=\""+email+"\"";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if(cursor.getCount()>0){
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
        }
        cursor.close();
        db.close();
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        Log.d(TAG,"Fetching user from SQLite: "+user.toString());
        if (email.equals(user.get("email"))&& pwd.equals(user.get("password"))){
            return true;
        }
        else {
            Log.d(TAG,"does not exist");
            return false;
        }
    }

}
