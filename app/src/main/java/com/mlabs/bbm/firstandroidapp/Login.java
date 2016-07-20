package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RalphPogi on 7/20/2016.
 */
public class Login extends AppCompatActivity {
    EditText editText,editText2;



@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.logn);

   editText = (EditText) findViewById(R.id.editText);
    editText2 = (EditText) findViewById(R.id.editText2);

    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {

             String email = editText.getText().toString();
             String pass = editText2.getText().toString();
            if (!isValidEmail(email) || !isValidPassword(pass)) {
                editText.setError("Invalid Email or Password");

            }



        }
    });
}


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}

