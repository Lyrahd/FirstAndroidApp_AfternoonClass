package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class registeractivity extends AppCompatActivity {
    private loginactivity loginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_acc);

        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmpass = (EditText) findViewById(R.id.confirmpass);

        final Button btn_reg = (Button) findViewById(R.id.btn_reg);

        final String emailInput = email.getText().toString().trim();
        final String passwordInput = password.getText().toString().trim();
        final String passwordInputVerify = confirmpass.getText().toString().trim();

        btn_reg.setOnClickListener(view);{
                Log.d(getApplicationContext().toString(), "CLICK");
                if (!emailInput.isEmpty() && !passwordInput.isEmpty() && !passwordInputVerify.isEmpty()) {
                    Log.d(getApplicationContext().toString(), "PAU");
                    if (passwordInput.equals(passwordInputVerify)) {
                        Log.d(registeractivity.this.toString(), "Signing Up..");
                        sqlDB.registerUser(emailInput, passwordInput, getCurrentDateTime());
                        Toast.makeText(getApplicationContext(), "User successfully added", Toast.LENGTH_LONG).show();
                        Intent goBackToLoginScreen = new Intent(getApplicationContext(), loginactivity.class);
                        startActivity(goBackToLoginScreen);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill up required fields", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

