package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by androidstudio on 17/09/16.
 */
public class signupactivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final databaseAdapter sqlDB = new databaseAdapter(getApplicationContext());

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.pass);
        EditText confirmPassword = (EditText) findViewById(R.id.cpass);
        Button btnRegister = (Button) findViewById(R.id.btnregister);
    }

}


