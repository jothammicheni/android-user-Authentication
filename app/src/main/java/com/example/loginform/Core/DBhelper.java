package com.example.loginform.Core;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERDB";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names


    // SQL statement to create the table


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RegisterContact.RegisterUserEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to upgrade the database, you can handle it here.
        // This method is called when the DATABASE_VERSION is increased.
    }

     public long insertData(String tableName, ContentValues values){
         SQLiteDatabase db = this.getWritableDatabase();

         try {
             return db.insertOrThrow(tableName, null, values);
         } catch (SQLException e) {
             e.printStackTrace();
             Log.e("DBHelper", "Error inserting data: " + e.getMessage()); // Log the error message
             return -1;
         } finally {
             db.close();
         }

     }
}
