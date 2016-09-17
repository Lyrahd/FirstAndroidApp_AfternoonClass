package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class SignUp extends AppCompatActivity {
    EditText emailsu, passu, conpassu;
    Button buttonsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailsu = (EditText) findViewById(R.id.email_txt);
        passu = (EditText) findViewById(R.id.pword_txt);
        conpassu = (EditText) findViewById(R.id.cpword_txt);
        buttonsu = (Button) findViewById(R.id.signup_btn);

        assert buttonsu != null;
        buttonsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((isValidPassword(passu.getText().toString()))!=(isValidConPassword(conpassu.getText().toString())))  {
                    Toast.makeText(SignUp.this, "Password do not match", Toast.LENGTH_LONG).show(); }
                else if(!isValidEmail(emailsu.getText().toString())) {
                    Toast.makeText(SignUp.this,"Invalid Email",Toast.LENGTH_LONG).show();
                } else if(!isValidPassword(passu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Invalid Password", Toast.LENGTH_LONG).show();
                }

                else{
                    Intent intent = new Intent(v.getContext(), Homepageb.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }
    private boolean isValidConPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }



}

