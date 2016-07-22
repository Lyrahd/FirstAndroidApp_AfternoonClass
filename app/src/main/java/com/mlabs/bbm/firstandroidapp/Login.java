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
    Button Button;
    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editText1=(EditText) findViewById(R.id.usertxt);
        editText2=(EditText) findViewById(R.id.passwordtxt);
        Button=(Button) findViewById(R.id.Buttonlog);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText() == null || !Patterns.EMAIL_ADDRESS.matcher(editText1.getText()).matches()) {
                    Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                } else {
                    if (editText2.getText().length() == 6) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), "Need 6 Characters", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}

