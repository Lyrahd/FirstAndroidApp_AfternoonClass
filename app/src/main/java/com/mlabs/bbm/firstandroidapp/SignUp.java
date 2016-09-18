package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by androidstudio on 9/17/16.
 */
public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmpass = (EditText) findViewById(R.id.confpassword);
        final Button validate = (Button) findViewById(R.id.okbtn);

        assert validate != null;
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email!");
                    email.requestFocus();

                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password!");
                    password.requestFocus();

                }

                if (password != confirmpass) {
                    Toast.makeText(SignUp.this, "Password does not match the confirm password.", Toast.LENGTH_SHORT).show();

                } else {

                }
            }
        });

    }

    private boolean validateEmail(String email) {
        String emailRegex;
        Pattern pattern;

        emailRegex = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }
    private boolean validatePassword(String password){
        if(password!=null && password.length()>7){
            return true;
        }
        else {
            return false;
        }
    }
}