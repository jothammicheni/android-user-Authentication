package com.example.loginform.Core;

import android.provider.BaseColumns;

public class RegisterContact {
    private RegisterContact(){}

       public static class  RegisterUserEntry implements BaseColumns{

           public static final String TABLE_NAME = "users";
           public static final String COLUMN_ID = "_id";
           public static final String COLUMN_USERNAME = "username";
           public static final String COLUMN_PASSWORD = "password";
           public static final String COLUMN_EMAIL= "email";


           public static final String CREATE_TABLE =
                   "CREATE TABLE " + TABLE_NAME + " (" +
                           COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           COLUMN_USERNAME + " TEXT, " +
                           COLUMN_EMAIL + " TEXT, " +
                           COLUMN_PASSWORD + " TEXT);";

       }


}
