package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;
import com.example.loginform.Core.DBhelper;
import com.example.loginform.Core.RegisterContact;
//import com.example.loginform.Core.register;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  EditText name ,email;
  TextView moveToregister,moveToLogin;
 Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.password);
        name=findViewById(R.id.emailEditText);
        submit=findViewById(R.id.login);
        moveToregister=findViewById(R.id.Toregister);

         moveToregister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getApplicationContext(),register.class);
                 startActivity(intent);
             }
         });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") ||
                        name.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Fill All the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String Email = email.getText().toString();
                String userName = name.getText().toString();

                // Check if the user exists in the database
                DBhelper dbHelper = new DBhelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {
                        RegisterContact.RegisterUserEntry.COLUMN_USERNAME,
                        RegisterContact.RegisterUserEntry.COLUMN_EMAIL
                };

                String selection = RegisterContact.RegisterUserEntry.COLUMN_USERNAME + " = ? AND " +
                        RegisterContact.RegisterUserEntry.COLUMN_EMAIL + " = ?";
                String[] selectionArgs = {userName, Email};

                Cursor cursor = db.query(
                        RegisterContact.RegisterUserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                if (cursor.moveToFirst()) {
                    // User exists, perform login
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Add code to navigate to the next screen or perform further actions after successful login
                } else {
                    // User does not exist
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();
            }
        });
    }

}