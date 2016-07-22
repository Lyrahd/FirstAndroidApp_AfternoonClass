package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Btnlogin ;
        final EditText EmailADD, PwD;

        EmailADD = (EditText)findViewById(R.id.editText);
        PwD = (EditText)findViewById(R.id.editText2);
        Btnlogin = (Button)findViewById(R.id.button);
        if (Btnlogin != null) {
            Btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(EmailADD.getText()).matches()) {
                        if (!(PwD.length() == 0)) {
                            if (PwD.length() > 8) {
                                Intent intent = new Intent(MainActivity.this, form.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Password field is empty", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getBaseContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();


                    /*Toast.makeText(getBaseContext(),, Toast.LENGTH_SHORT).show();*/
                }
            });
        }

        }
}


