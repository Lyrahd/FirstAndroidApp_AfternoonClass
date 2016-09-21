package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 7/20/2016.
 */
public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        Button btnlogin = (Button) findViewById(R.id.btn_login);
        TextView btnsignup = (TextView) findViewById(R.id.sign_up);
        final EditText un = (EditText) findViewById(R.id.username);
        final EditText pwd = (EditText) findViewById(R.id.password);

        btnsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), SignActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                
            }
        });
    }

}