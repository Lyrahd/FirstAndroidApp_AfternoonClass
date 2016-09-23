package com.mlabs.bbm.firstandroidapp;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
    //private static final String KEY_CREATED_AT ="created_at";


    public DBHelp(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DBHelp(Context context, String databaseName, SQLiteDatabase.CursorFactory o, int databaseVersion) {
        super(context, databaseName, o, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB){
        String CREATE_USER_TABLE ="CREATE TABLE " +  TABLE_USER  + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT UNIQUE,"
                + KEY_PASSWORD + " TEXT" + ")";
                //+ KEY_CREATED_AT + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database Tables Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);

        onCreate(db);
    }
/**
    public void registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        //values.put(KEY_CREATED_AT, created_at);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "Successfully Added user: " + id);

    }

    public String getUserName(String UserName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + TABLE_USER
                + " WHERE " +
                KEY_USERNAME + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Cursor cursor = db.rawQuery(selectQuery, new String[] {UserName});
        if(cursor.getCount()>1){
            cursor.close();
            return "Does Not Exist";
        }
        cursor.moveToFirst();
        String pass = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return pass;
    }
    **/
}