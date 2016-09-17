package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity {

    Button validate;
    TextView showpass;

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
        if (password != null && password.length() > 7) {
            return true;
        } else
            return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pass);
        showpass = (TextView) findViewById(R.id.show);
        validate = (Button) findViewById(R.id.buttonL);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "WELCOME !!!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            }
        });


       showpass.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(null);
                        break;

                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        break;
                }
                return true;
            }
        });
    }
}










