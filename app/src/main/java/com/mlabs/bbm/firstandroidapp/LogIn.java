package com.mlabs.bbm.firstandroidapp;

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

public class LogIn extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }


    public void login(View view) {
        final EditText email = (EditText) findViewById(R.id.emailTxt);
        final EditText pwd = (EditText) findViewById(R.id.pwdTxt);
        final Button loginbutton = (Button) findViewById(R.id.logInBtn);

                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePwd(pwd.getText().toString())) {
                    pwd.setError("Invalid Password");
                    pwd.requestFocus();
                } else {
                    Intent intent=new Intent(LogIn.this,MainActivity.class);
                    startActivity(intent);
                }
            }

            private Boolean validateEmail(String emailAdd){
                if(emailAdd==null||!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
                    return false;
                }
                else {
                    return true;
                }
            }

            private Boolean validatePwd(String password){
                if(password!=null&&password.length()>=8){
                    return true;
                }
                else {
                    return false;
                }
            }

        }