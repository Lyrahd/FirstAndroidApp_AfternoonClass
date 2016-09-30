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
import android.widget.Toast;


public class LogIn extends Activity {

    EditText pwdTxt,emailTxt;

    Button showbtn,logInBtn;
    String uname,pwd;

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
        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());


       if (!validatePwd(pwdTxt.getText().toString())) {
            pwdTxt.setError("Invalid Password");
            pwdTxt.requestFocus();
        }

        if (sqlDB.validateUser(emailTxt.getText().toString(),pwdTxt.getText().toString())) {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
        }


    }



        private Boolean validatePwd(String password) {
            if (password != null && password.length() >= 8) {
                return true;
            } else {
                return false;
            }
        }


    public void SignUp(View v) {
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
    }



}
