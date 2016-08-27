package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LogIn extends Activity {
    EditText pwdTxt,emailTxt;

    Button showbtn,logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        showbtn = (Button) findViewById(R.id.showbtn);
        pwdTxt = (EditText) findViewById(R.id.pwdTxt);
        emailTxt=(EditText) findViewById(R.id.emailTxt);
        logInBtn=(Button) findViewById(R.id.logInBtn);

    showbtn.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            //int event = motionEvent.getAction();
            final int cursor=pwdTxt.getSelectionStart();
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("Classname","ACTION_DOWN");
                    pwdTxt.setTransformationMethod(null);
                    pwdTxt.setSelection(cursor);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("Classname","ACTION_UP");
                    pwdTxt.setTransformationMethod(new PasswordTransformationMethod());
                    pwdTxt.setSelection(cursor);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    pwdTxt.setSelection(cursor);
                    break;
                default:
                    break;
            }

            //if (event == motionEvent.ACTION_DOWN) {
            //    pwdTxt.setTransformationMethod(null);
            //    return true;
            //} else if(event==motionEvent.ACTION_UP){
            //    pwdTxt.setTransformationMethod(new PasswordTransformationMethod());
            //    return true;
            //}
            return true;
        }
    });
    }

    public void login(View view) {

        if (!validateEmail(emailTxt.getText().toString())) {
            emailTxt.setError("Invalid Email");
            emailTxt.requestFocus();
        } else if (!validatePwd(pwdTxt.getText().toString())) {
            pwdTxt.setError("Invalid Password");
            pwdTxt.requestFocus();
        } else {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
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
