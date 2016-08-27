package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;

public class MainActivity extends AppCompatActivity {
    Button sign, exit;
    EditText etext, ptext;
    TextView tv7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign = (Button) findViewById(R.id.button);
        etext = (EditText) findViewById(R.id.editText);
        ptext = (EditText) findViewById(R.id.editText2);
        tv7 = (TextView) findViewById(R.id.textView7);

        tv7.setOnTouchListener(new View.OnTouchListener() {
                                   @Override
                                   public boolean onTouch(View view, MotionEvent event) {
                                       switch (event.getAction()) {
                                           case MotionEvent.ACTION_DOWN:
                                               ptext.setTransformationMethod(null);
                                               break;
                                           case MotionEvent.ACTION_UP:
                                               ptext.setTransformationMethod(new PasswordTransformationMethod());
                                               break;
                                       }
                                       return true;
                                   }
                               }
        );
        sign.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (etext.getText().toString().equals("veeldyow@yahoo.com") && ptext.getText().toString().equals("catiis")) {
                                            Toast.makeText(getApplicationContext(), "Redirecting....", Toast.LENGTH_SHORT).show();

                                            Thread timerThread = new Thread() {
                                                public void run() {
                                                    try {
                                                        sleep(1000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } finally {
                                                        Intent nextmo = new Intent(MainActivity.this, WelcomeScreen.class);
                                                        startActivity(nextmo);
                                                    }
                                                }
                                            };
                                            timerThread.start();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Incorrect Email/Password", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
        );
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}