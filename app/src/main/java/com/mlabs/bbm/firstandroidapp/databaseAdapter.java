package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by androidstudio on 17/09/16.
 */
public class databaseAdapter {

    private static final String TAG = databaseAdapter.class.getSimpleName();

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "users";

    private static final String KEY_ID = "userId";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DATECREATED = "date_created";


    public databaseAdapter(Context _context) {


        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + "INTEGER PRIMARY KEY, "  + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT," + KEY_DATECREATED + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }

    public void registerUser(String email,String password, String created_at){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); 
        values.put(KEY_PASSWORD, password); 
        values.put(KEY_DATECREATED, created_at);
        long id = db.insert(TABLE_USER, null, values); 
        db.close();  
        Log.d(TAG, "Successfully Added user: " + id;
    }

}
