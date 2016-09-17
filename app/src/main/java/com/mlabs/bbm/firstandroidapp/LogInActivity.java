package com.mlabs.bbm.firstandroidapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    TextView show;
    TextView SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.login_activity);

        final EditText email_ad = (EditText) findViewById(R.id.editText);
        final EditText password_tu = (EditText) findViewById(R.id.editText2);
        Button validate = (Button) findViewById(R.id.buttonok);
        show = (TextView) findViewById(R.id.show);

        SignUp = (TextView) findViewById(R.id.SignUp);


        assert validate != null;
        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!validateEmail(email_ad.getText().toString())){
                    email_ad.setError("Invalid Email");
                    email_ad.requestFocus();
                }else if(!validatePassword(password_tu.getText().toString())){
                    password_tu.setError("Invalid Password");
                    password_tu.requestFocus();

                }else{

                    Intent intent = new Intent(LogInActivity.this,SignUpActivity.class );
                    startActivity(intent);

                }

            }

        });

        show.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int cursor= password_tu.getSelectionStart();


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password_tu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password_tu.setSelection(cursor);
                        break;

                    case MotionEvent.ACTION_UP:
                        password_tu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password_tu.setSelection(cursor);
                        break;


                }
                return true;
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(LogInActivity.this,SignUpActivity.class );
                    startActivity(intent);

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



