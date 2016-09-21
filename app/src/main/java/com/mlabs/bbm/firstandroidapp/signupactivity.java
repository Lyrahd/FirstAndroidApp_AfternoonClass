package com.mlabs.bbm.firstandroidapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class signupactivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final databaseAdapter sqlDB = new databaseAdapter(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.pass);
        final EditText confirmPassword = (EditText) findViewById(R.id.cpass);
        Button btnRegister = (Button) findViewById(R.id.btnregister);

        findViewById(R.id.btnregister).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

     //   if ((isValidPassword(password)== isValidPassword1(confirmPassword)) && isValidEmail(email)) {


           // Intent i = new Intent(signupactivity.this, MainActivity.class);
          //  startActivity(i);
      //  }
            }
        });
    }

    private boolean isValidEmail(EditText email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher((CharSequence) email);
        return matcher.matches();
    }

    private boolean isValidPassword1(EditText confirmPassword) {
        if (confirmPassword != null && confirmPassword.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidPassword(EditText password) {
        if (password != null && password.length() > 6) {
            return true;
        }
        return false;
    }







}


