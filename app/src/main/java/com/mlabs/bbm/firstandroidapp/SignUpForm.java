package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        final EditText email = (EditText) findViewById(R.id.S_email);
        final EditText password = (EditText) findViewById(R.id.S_pword);
        final EditText con_password = (EditText) findViewById(R.id.S_Cpword);
        Button btnsignup = (Button) findViewById(R.id.btnsignup);


        //        button login
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                }
                else if(!validatePassword(password.getText().toString())){
                    password.setError("Invalid Password");
                    password.requestFocus();
                }
                else if(!password.getText().toString().equals(con_password.getText().toString())) {
                    con_password.setError("Password does not match");
                    password.requestFocus();
                }
                else {
                    Intent i = new Intent(SignUpForm.this, Main2Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    //      validating email
    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    //      validate password
//    return true of the passwrod is valid
    protected boolean validatePassword(String password) {
        if(password!=null && password.length() >8) {
            return true;
        } else {
            return false;
        }
    }
}
