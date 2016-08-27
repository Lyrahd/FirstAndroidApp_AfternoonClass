package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 7/20/2016.
 */
public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        Button button1 = (Button) findViewById(R.id.button1);
        Button show = (Button) findViewById(R.id.button);

        final EditText editemail = (EditText) findViewById(R.id.editText);
        final EditText editpass = (EditText) findViewById(R.id.editText2);

        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editemail.getText().toString();
                String passa = editpass.getText().toString();
                if ((!isValidEmail(email)) || (!isValidPassword(passa))) {
                    Toast.makeText(LogInActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });


        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventA = motionEvent.getAction();

/*                if (eventA == motionEvent.ACTION_UP) {
                    editpass.setTransformationMethod(new PasswordTransformationMethod());
                } else if (eventA == motionEvent.ACTION_CANCEL) {
                    editpass.setTransformationMethod(new PasswordTransformationMethod());
                } else if (eventA == motionEvent.ACTION_DOWN) {
                    editpass.setTransformationMethod(null);
                }
                return true; */

                switch (eventA) {
                    case MotionEvent.ACTION_DOWN:
                        editpass.setTransformationMethod(null);
                          break;
                    case MotionEvent.ACTION_UP:
                        editpass.setTransformationMethod(new PasswordTransformationMethod());
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        editpass.setTransformationMethod(new PasswordTransformationMethod());
                        break;
                }
                return true;
            }
        });
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$#]).{6,20})";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

}