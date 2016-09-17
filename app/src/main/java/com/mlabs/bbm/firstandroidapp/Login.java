package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Alien on 7/22/2016.
 */
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText TF=(EditText) findViewById(R.id.editText);
        final EditText TF2=(EditText) findViewById(R.id.editText2);
        final Button b=(Button) findViewById(R.id.button);
        final Button sign=(Button) findViewById(R.id.signup);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(Login.this, signup.class);

                        startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TF.getText() == null || !Patterns.EMAIL_ADDRESS.matcher(TF.getText()).matches()) {
                    Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                } else {
                    if (TF2.getText().length() == 10) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), "10 Characters Needed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}
