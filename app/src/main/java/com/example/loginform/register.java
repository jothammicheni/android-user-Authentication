package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;
import com.example.loginform.Core.DBhelper;
import com.example.loginform.Core.RegisterContact;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
     Button registerBtn;
     EditText editName,editEmail,editPassword;
     TextView moveToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail=findViewById(R.id.editTextEmail);
        editPassword=findViewById(R.id.editTextPassword);
        editName=findViewById(R.id.editTextName);
        registerBtn=findViewById(R.id.registerBtn);


        moveToLogin=findViewById(R.id.backToLogin);
        moveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
registerBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (editEmail.getText().toString().equals("") &&
                editName.getText().toString().equals("") &&
                editPassword.getText().toString().equals("") )
        {
            Toast.makeText(register.this, "Fill All the fields", Toast.LENGTH_SHORT).show();
        }
        else{
            DBhelper dbHelper = new DBhelper(register.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            String email = editEmail.getText().toString();

            // Check if the room already exists
            Cursor cursor = db.rawQuery("SELECT * FROM " + RegisterContact.RegisterUserEntry.TABLE_NAME +
                    " WHERE " + RegisterContact.RegisterUserEntry.  COLUMN_EMAIL + "=?", new String[]{email});

            if (cursor.moveToFirst()) {
                // Room already exists in the database
                Toast.makeText(register.this, "Username already exists", Toast.LENGTH_SHORT).show();
            }else{
                String Email = editEmail.getText().toString();
                String name = editName.getText().toString();
                String password = editPassword.getText().toString();

                ContentValues values = new ContentValues();
                values.put(RegisterContact.RegisterUserEntry.COLUMN_EMAIL, Email);
                values.put(RegisterContact.RegisterUserEntry.COLUMN_PASSWORD,password);
                values.put(RegisterContact.RegisterUserEntry.COLUMN_USERNAME, name);

            long newUser=db.insert(RegisterContact.RegisterUserEntry.TABLE_NAME,null,values);

            if(newUser!=-1){
                Toast.makeText(register.this, "Register successfull", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(register.this, "Register failed", Toast.LENGTH_SHORT).show();
            }
            }
        }
    }
});
    }
}