package com.mlabs.bbm.firstandroidapp;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity{
    private LogIn Login;

    EditText passTxt,emailTxt,confirmpassTxt;
    String email,pwd,confirmpwd;
    Button showbtn11,showbtn12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        showbtn11 = (Button) findViewById(R.id.showbtn1);
        showbtn12 = (Button) findViewById(R.id.showbtn2);
        passTxt = (EditText) findViewById(R.id.passText);
        emailTxt = (EditText) findViewById(R.id.emailText);
        confirmpassTxt = (EditText) findViewById(R.id.confirmText);
        showbtn11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                final int cursor=passTxt.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        passTxt.setTransformationMethod(null);
                        passTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        passTxt.setTransformationMethod(new PasswordTransformationMethod());
                        passTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        passTxt.setSelection(cursor);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
        showbtn12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                final int cursor=confirmpassTxt.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        confirmpassTxt.setTransformationMethod(null);
                        confirmpassTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        confirmpassTxt.setTransformationMethod(new PasswordTransformationMethod());
                        confirmpassTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        confirmpassTxt.setSelection(cursor);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });





    }


    public void register(View view) {
        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());
        email = emailTxt.getText().toString().trim();
        pwd = passTxt.getText().toString().trim();
        confirmpwd = confirmpassTxt.getText().toString().trim();

        if (!email.isEmpty() && !pwd.isEmpty() && !confirmpwd.isEmpty()) {
            if (pwd.equals(confirmpwd)) {
                if (!validateEmail(email)) {
                    emailTxt.setError("Invalid Email");
                    emailTxt.requestFocus();
                }else if (!validatePwd(pwd)) {
                    passTxt.setError("Atleast 8 characters");
                    passTxt.requestFocus();

                } else {
                    Log.d(SignupActivity.this.toString(),"Signing up...");
                    String msg=sqlDB.registerUser(email,pwd);
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    if(msg.equals("User successfully added")) {
                        Intent goBackToLogIn = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(goBackToLogIn);
                    } else{
                        emailTxt.setText("");
                        passTxt.setText("");
                        confirmpassTxt.setText("");
                        emailTxt.requestFocus();
                    }
                }

            }
            else {
                Toast.makeText(getApplicationContext(),"Password did not match", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Please fill up all fields",Toast.LENGTH_LONG).show();
        }


    }



    private Boolean validateEmail(String emailAdd) {
        if (emailAdd == null || !Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean validatePwd(String password) {
        if (password != null && password.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }
}


