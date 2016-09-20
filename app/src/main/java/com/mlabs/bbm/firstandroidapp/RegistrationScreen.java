package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class RegistrationScreen extends AppCompatActivity {
    EditText email, password, password2;
    Button btnRegister;
    DBAdapter DatabaseAdapter;

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);


        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPass);
        password2 = (EditText) findViewById(R.id.txtPass2);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        DatabaseAdapter = new DBAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();

        assert btnRegister != null;
       btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((isValidPassword(password.getText().toString()))!=(isValidConPassword(password2.getText().toString())))  {
                    Toast.makeText(RegistrationScreen.this, "Password do not match", Toast.LENGTH_LONG).show(); }
                else if(!isValidEmail(email.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this,"Invalid Email",Toast.LENGTH_LONG).show();
                } else if(!isValidPassword(password.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this, "Invalid Password", Toast.LENGTH_LONG).show();
                }

                else if(password.getText().toString().equals(password2.getText().toString())){
                    DatabaseAdapter.insertEntry(email.getText().toString(),password.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(RegistrationScreen.this,LoginScreen.class );
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }
    private boolean isValidConPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }



}