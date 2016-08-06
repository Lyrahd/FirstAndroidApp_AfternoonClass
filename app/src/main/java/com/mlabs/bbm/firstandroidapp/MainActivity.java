package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText editUser;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText) findViewById(R.id.editUser);
        editPass = (EditText) findViewById(R.id.editPass);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 */
                if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editUser.getText()).matches() && editPass.length()>=6)
                {
                    Intent i = new Intent(MainActivity.this,Display.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                }
                /**
                 * for email validation ONLY + password
                 */
                if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editUser.getText()).matches() && editPass.length()>=6){
                    Intent i = new Intent(MainActivity.this,Display.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$#]).{6,20})";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}

