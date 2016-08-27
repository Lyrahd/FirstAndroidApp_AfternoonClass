package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login_button;
    int attempt_counter = 3;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.email_address);
        password = (EditText) findViewById(R.id.pwd);
        login_button = (Button) findViewById(R.id.btn);
        show =(Button)findViewById(R.id.btnShow);

        attempt_counter = attempt_counter + 1;

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
            }
        });

        /*password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
            }
        }); */

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(new Intent(MainActivity.this, New.class));
                }
                else{
                    attempt_counter--;
                    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

                    login_button.setEnabled(true);

                    if (attempt_counter == 0){
                        Toast.makeText(getBaseContext(), "Already reached maximum attempts", Toast.LENGTH_LONG).show();

                        login_button.setEnabled(false);

                    }
                }
            }
        });
        show.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        final int cursor = password.getSelectionStart();
                                        int event = motionEvent.getAction();


                                       /* if (event == motionEvent.ACTION_DOWN) {
                                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                        } else if (event ==motionEvent.ACTION_UP){
                                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                        }
                                        return false;
                                        */
                                        switch(event){
                                            case MotionEvent.ACTION_DOWN:
                                                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                                password.setSelection(cursor);
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                                password.setSelection(cursor);
                                                break;
                                           //case MotionEvent.ACTION_CANCEL:
                                               // password.setTransformationMethod(new PasswordTransformationMethod());
                                        }
                                        return false;
                                    }
                                }

        );

    }
}