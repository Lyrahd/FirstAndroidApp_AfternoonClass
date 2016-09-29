package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {
    DBAdapter DBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText FirstName= (EditText) findViewById(R.id.txtFName);
        final EditText Username= (EditText) findViewById(R.id.txtUName);
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText password = (EditText) findViewById(R.id.lblPass);


        Button login = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);
        final TextView SignUp = (TextView) findViewById(R.id.lblSignUp);

        DBAdapter = new DBAdapter(this);
        DBAdapter = DBAdapter.open();


        assert login != null;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = FirstName.getText().toString();
                String pass = password.getText().toString();
                String uname = Username.getText().toString();
                String Password = DBAdapter.getSinlgeEntry(email);
                String Password1 = DBAdapter.getUsername(uname);

                if (pass.equals(Password1)|pass.equals(Password)) {
                    Toast.makeText(LoginScreen.this, uname + " has logged in. \n Password: " + pass, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginScreen.this, "invalid email or password ", Toast.LENGTH_LONG).show();
                }
            }

        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(LoginScreen.this, RegistrationScreen.class);
                startActivity(i);
            }

        });

        btnShow.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        final int cursor = password.getSelectionStart();
                                        int event = motionEvent.getAction();

                                        switch (event) {
                                            case MotionEvent.ACTION_DOWN:
                                                password.setTransformationMethod(null);
                                                password.setSelection(cursor);
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                password.setTransformationMethod(new PasswordTransformationMethod());
                                                password.setSelection(cursor);
                                                break;
                                        }

                                        return false;
                                    }
                                }
        );
    }


    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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




