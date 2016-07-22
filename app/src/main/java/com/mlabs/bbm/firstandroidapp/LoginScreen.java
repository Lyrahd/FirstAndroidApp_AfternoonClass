package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);


        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText password = (EditText) findViewById(R.id.txtPassword);
        Button login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Intent intent = new Intent(LoginScreen.this,MainActivity.class );
                    startActivity(intent);
                }

            }

            //Return true if password is valid and false if password is invalid.
            private boolean validatePassword(String password) {
                if (password != null && password.length() > 9) {
                    return true;
                } else {
                    return false;
                }
            }

            //Return true if password is valid and false if password is invalid.
            private boolean validateEmail(String email) {
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(email);

                return matcher.matches();
            }


        });
    }
}
