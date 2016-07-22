package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import  android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;



public class MainActivity extends Activity {
    EditText username;
    EditText password;
    Button login_button;
    int attempt_counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.txt_UName);
        password = (EditText) findViewById(R.id.txt_PWord);
        login_button = (Button) findViewById(R.id.btn_LogIn);

        attempt_counter = attempt_counter + 1;

        password.setInputType(InputType.TYPE_CLASS_TEXT);
        username.setTextColor(Color.GRAY);
        password.setTextColor(Color.GRAY);
        username.setText("Enter username");
        password.setText("Enter password");

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_button.setBackgroundColor(Color.GRAY);
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                }
                else{
                    attempt_counter--;
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    username.setText("Enter username");
                    password.setText("Enter password");
                    login_button.setEnabled(true);
                    login_button.setBackgroundColor(Color.BLACK);
                    if (attempt_counter == 0){
                        Toast.makeText(getBaseContext(), "Already reached maximum attempts", Toast.LENGTH_LONG).show();
                        username.setText("Enter username");
                        password.setText("Enter password");
                        login_button.setEnabled(false);
                        login_button.setBackgroundColor(Color.LTGRAY);
                    }
                }
            }
        });
    }
}


