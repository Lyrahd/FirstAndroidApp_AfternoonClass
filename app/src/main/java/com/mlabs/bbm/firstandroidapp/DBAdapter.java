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
            "FIRSTNAME text,SURNAME text,USERNAME text,EMAIL  text,PASSWORD text); ";
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

    public void insertEntry(String email, String password, String fname, String surname, String uname) {
        ContentValues newValues = new ContentValues();
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        newValues.put("FIRSTNAME", fname);
        newValues.put("SURNAME", surname);
        newValues.put("USERNAME", uname);
        db.insert("USERS", null, newValues);
    }

    public int deleteEntry(String email) {
        String where = "EMAIL=?";
        int numberOFEntriesDeleted = db.delete("USERS", where, new String[]{email});
        Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSingleEntry(String email) {
        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "Account do not ExistT";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String email, String pword) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("EMAIL", email);
        updatedValues.put("PASSWORD", pword);

        String where = "EMAIL = ?";
        db.update("USERS", updatedValues, where, new String[]{email});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+"USERS",null);
        return res;
    }
}