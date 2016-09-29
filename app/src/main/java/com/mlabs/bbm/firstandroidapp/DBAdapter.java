package com.mlabs.bbm.firstandroidapp;

/**
 * Created by DarkHorse on 21/09/2016.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBAdapter {
    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "Create Table " + "USERS" +
            "( " + "ID" + " Integer Primary Key Autoincrement," +
            "FNAME text,LNAME text,UNAME text,EMAIL  text,PASSWORD text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DBHelper dbHelper;

    public DBAdapter(Context _context) {
        context = _context;
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String fname, String lname, String uname,String email, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("FNAME", fname);
        newValues.put("LNAME", lname);
        newValues.put("UNAME", uname);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        db.insert("USERS", null, newValues);
        Toast.makeText(context, " Successfully Saved", Toast.LENGTH_LONG).show();
    }



    public String getSinlgeEntry(String email) {
        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public String getUsername(String uname) {


        Cursor cursor = db.query("USERS", null, " UNAME=?", new String[]{uname}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }



    public String getUsernameforsignup(String uname) {


        Cursor cursor = db.query("USERS", null, " UNAME=?", new String[]{uname}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("UNAME"));
        cursor.close();
        return password;
    }

    public String getEmailforsignup(String email) {


        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {

            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return password;
    }

    public void updateEntry(String email, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("EMAIL", email);
        updatedValues.put("PASSWORD", password);

        String where = "EMAIL = ?";
        db.update("USERS", updatedValues, where, new String[]{email});
    }
}