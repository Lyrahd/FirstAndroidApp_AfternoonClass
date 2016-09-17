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
public class SignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Button buttonok = (Button) findViewById(R.id.btnok);
        final EditText etemail = (EditText) findViewById(R.id.ETun);
        final EditText etpass = (EditText) findViewById(R.id.ETpass);
        final EditText etcp = (EditText) findViewById(R.id.ETcp);

        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String passa = etpass.getText().toString();
                String cpass = etcp.getText().toString();
                if ((!isValidEmail(email)) || (!isValidPassword(passa))) {
                    Toast.makeText(SignActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
                else if (cpass==passa){
                    Toast.makeText(SignActivity.this, "Password Does not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });


    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$#]).{6,20})";

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
}