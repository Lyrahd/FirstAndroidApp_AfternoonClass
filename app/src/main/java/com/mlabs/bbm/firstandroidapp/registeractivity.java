package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/2016.
 */
public class registeractivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlogin;
        final EditText EmailAdd, PassW;


        EmailAdd = (EditText) findViewById(R.id.editText);
        PassW = (EditText) findViewById(R.id.editText2);
        btnlogin = (Button) findViewById(R.id.button2);
        Button btn1 = (Button) findViewById(R.id.tv_lbl1);


        if (btnlogin != null) {
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(EmailAdd.getText()).matches() && (Pattern.compile("([a-zA-Z0-9]+)").matcher(PassW.getText()).matches()) && PassW.length() >= 8) {
                        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginactivity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
