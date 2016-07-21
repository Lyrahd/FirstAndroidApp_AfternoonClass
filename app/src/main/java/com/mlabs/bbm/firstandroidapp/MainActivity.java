package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);

//        button login
       btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if(!validatePassword(password.getText().toString())){
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
//                    Toast.makeText(MainActivity.this,"Validation Success",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(MainActivity.this, body.class);
                        Intent i = new Intent(MainActivity.this, homepage.class);
                        startActivity(i);
                        finish();
                }
            }
        });
    }

    //      validate email
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    //      validate password
//    return true of the passwrod is valid
    protected boolean validatePassword(String password) {
        if(password!=null && password.length() >6) {
            return true;
        } else {
            return false;
        }
    }
}

