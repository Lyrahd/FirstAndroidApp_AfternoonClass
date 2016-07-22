package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = (EditText) findViewById(R.id.txt_un);
        final EditText password = (EditText) findViewById(R.id.txt_pw);
        Button validate = (Button) findViewById(R.id.btn_login);

        assert validate != null;
        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validateEmail(username.getText().toString())){
                    username.setError("Invalid Email");
                    username.requestFocus();
                }else if(!validatePassword(password.getText().toString())){
                    password.setError("Invalid Password");
                    password.requestFocus();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Your toast message.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this,SplashScreen .class );
                    startActivity(intent);
                }
            }

        });
    }

    private boolean validateEmail(String email) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(email);
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

    }

}
