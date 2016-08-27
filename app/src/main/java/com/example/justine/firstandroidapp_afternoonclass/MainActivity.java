package com.example.justine.firstandroidapp_afternoonclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        Button show = (Button) findViewById(R.id.show);
        // Button for Show
        show.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motion){
                switch ( motion.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
                /*int EventAct = motion.getAction();

                if (EventAct == MotionEvent.ACTION_UP) {

                    password.setTransformationMethod(new PasswordTransformationMethod());
                } else if (EventAct == MotionEvent.ACTION_CANCEL) {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                } else if (EventAct == MotionEvent.ACTION_DOWN) {
                    password.setTransformationMethod(null);
               }
                return true;*/
            }
        });
        // Button for Login
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email != null && password != null) {
                    if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(email.getText()).matches()) {
                        if (password.length() > 8) {
                            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(MainActivity.this, welcome.class);
                            startActivity(a);
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Invalid Email address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank field", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
