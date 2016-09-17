package com.mlabs.bbm.firstandroidapp;

import android.database.sqlite.SQLiteOpenHelper;

  

        import android.app.Activity;
         import android.content.ContentValues; 
        import android.content.Context;
         import android.database.Cursor; 
        import android.database.sqlite.SQLiteOpenHelper; 
        import android.database.sqlite.SQLiteDatabase;
         import android.util.Log;  import java.util.HashMap;  


public class DatabaseAdapter extends SQLiteOpenHelper {
     
    private static final String TAG = DatabaseAdapter.class.getSimpleName();
     
    //Database Name 
    private static final String DATABASE_NAME = "users.db";
     
    // DB version 
    private static final int DATABASE_VERSION = 1;
     
    // DB Table name 
    private static final String TABLE_USER = "user";
     
// Defining column names: 
    private static final KEY_ID="id";
    private static final KEY_EMAIL="email"; 
    private static final KEY_PASSWORD="password"; 
    private static final KEY_CREATED_AT="created_at";  

    public DatabaseAdapter(Context _context) { 
        super(_context, DATABASE_NAME, null, DATABASE_VERSION);  }  
// Define Database Table 
    @Override  
    public void onCreate(SQLiteDatabase sqlDB) { 
        String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "(" 
        +KEY_ID + "INTEGER PRIMARY KEY," 
        +KEY_EMAIL + " TEXT UNIQUE," 
        +KEY_PASSWORD + " TEXT," 
        +KEY_CREATED_AT + " TEXT," + ")"; 
        sqlDB.execSQL(CREATE_USER_TABLE);  
        Log.d(TAG, "Database tables created"); }

       
//Upgrading database this will happen if an update is available. 

    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
        //Drop older table if existed 
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);  
        //Recreate Table 
        onCreate(db);      }  
        //Creating new users: 
        public void registerUser (String email, String password, String created_at){ 
            SQLiteDatabase db = this.getWritableDatabase();  
            ContentValues values = new ContentValues(); 
            values.put(KEY_EMAIL, email); //email 
            values.put(KEY_PASSWORD, password); //password 
            values.put(KEY_CREATED_AT, created_at); //created_at  
            // Inserting Row 
            long id = db.insert(TABLE_USER, null, values); 
            db.close();
            //Close Database Connection  
            Log.d(TAG, "Successfully Added User: " + id); }  
        //Pulling records from Database 
        public boolean validateUser (String userName, String password){ 
            HashMap<String, String> user = new HashMap<String, String>(); 
            String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=" + userName;  
            SQLiteDatabase db = this.getReadableDatabase(); 
            Cursor cursor = db.rawQuery(selectQuery, null); 
            //Move to first row  
            if (cursor.getCount() > 0) { 
                user.put("email", cursor.getString(1)); 
                user.put("password", cursor.getString(2)); 
                user.put("created_at", cursor.getString(3)); } 
            cursor.close(); 
            db.close(); 
            //return user record 
            Log.d(TAG, "Fetching user from Sqlite: " + user.toString()); 

            // Toast.makeText(DatabaseAdapter.this, "Password is incorrect", Toast.LENGTH_SHORT).show(); 

            if (password.equals(user.get(password))) {  
                Log.d(TAG, "Password was validated "); 
                return true; 
            } 
            else
            {  
                Log.d(TAG, "Password mismatch"); 
                return false; 
            } 
        }      //Delete all registered user. 
        public void deleteUsers () { 
            SQLiteDatabase db = this.getWritableDatabase(); 
            //Delete all Rows 
            db.delete(TABLE_USER, null, null); 
            db.close(); 
            Log.d(TAG, "Deleted all user records from sqlite"); 
        } 
    }

     
}
