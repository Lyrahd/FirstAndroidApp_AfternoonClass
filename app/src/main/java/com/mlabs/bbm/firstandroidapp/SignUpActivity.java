package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends android.app.Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final EditText NewEmail = (EditText) findViewById(R.id.txt_Email);
        final EditText NewPassword = (EditText) findViewById(R.id.txt_Password);
        final EditText ConfirmPassword = (EditText) findViewById(R.id.txt_CPassword);
        Button CreateAcct = (Button) findViewById(R.id.btn_CreateAcct);


        assert CreateAcct != null;
        CreateAcct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validateEmail(NewEmail.getText().toString())){
                    NewEmail.setError("Invalid Email");
                    NewEmail.requestFocus();
                }else if(NewPassword.getText().toString() != ConfirmPassword.getText().toString()){
                   NewPassword.setError("Password does not match");
                    NewPassword.requestFocus();

                }else if(NewPassword.getText().toString().equals(ConfirmPassword.getText().toString())){

                    Intent intent = new Intent(SignUpActivity.this,LogInActivity.class );
                    startActivity(intent);

                }

            }

        });

        ConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                       if(NewPassword.getText().toString().length()<8){
                           NewPassword.setError("Password must be at least 8 characters");
                           NewPassword.requestFocus();
                           break;
                       }

                }
                return true;
            }
        });




    }

    private boolean validateEmail(String username) {
        String email_ad;
        Pattern pattern;

        email_ad = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";

        pattern = Pattern.compile(email_ad);
        Matcher matcher = pattern.matcher(username);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if(password !=null && password.length()>8){
            return true;
        }else
            return false;
    }}



