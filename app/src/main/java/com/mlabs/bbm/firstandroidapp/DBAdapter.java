package com.mlabs.bbm.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by androidstudio on 17/09/16.
 */
public class DBAdapter extends SQLiteOpenHelper {
    private static final String TAG = DBAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CREATED_AT = "created_at";
    String CREATE_USER_TABLE = "CREATE_TABLE"+TABLE_USER+"("
            + KEY_ID +"INTEGER PRIMARY KEY,"
            + KEY_EMAIL +"TEXT_UNIQUE,"
            + KEY_PASSWORD +"TEXT,"
            + KEY_CREATED_AT +"TEXT"+")";

    public DBAdapter(Context _context) {
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //define database table
    @Override
    public void onCreate(SQLiteDatabase sqlDB){
        String CREATE_USER_TABLE = "CREATE_TABLE"+TABLE_USER+"("
                + KEY_ID +"INTEGER PRIMARY KEY,"
                + KEY_EMAIL +"TEXT_UNIQUE,"
                + KEY_PASSWORD +"TEXT,"
                + KEY_CREATED_AT +"TEXT"+")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG,"Database tables created");
    }
    //upgrading database this will happen if an updates is available
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);

        onCreate(db);
    }
    //creating new user

}

