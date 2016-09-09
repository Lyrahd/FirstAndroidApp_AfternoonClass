package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

/**
 * Created by Pau on 7/21/2016.
 */
public class loginactivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlogin;
        final EditText EmailAdd, PassW;


        EmailAdd = (EditText) findViewById(R.id.editText);
        PassW = (EditText) findViewById(R.id.editText2);
        btnlogin = (Button) findViewById(R.id.button2);
        Button btn1 = (Button) findViewById(R.id.tv_lbl1);


        if (btnlogin != null) {
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(EmailAdd.getText()).matches() && (Pattern.compile("([a-zA-Z0-9]+)").matcher(PassW.getText()).matches()) && PassW.length() >= 8) {
                        Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginactivity.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        PassW.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        PassW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });
    }
            public static boolean isValidEmail(CharSequence target) {
                if (TextUtils.isEmpty(target)) {
                    return false;
                } else {
                    return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
                }

            }

            protected void onPause() {
                super.onPause();
                finish();
            }
    }



