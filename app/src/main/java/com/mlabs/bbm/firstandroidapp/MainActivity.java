package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import java.lang.*;
import android.app.Activity;  // For removing title bar
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window; // For removing title bar
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import java.util.regex.Matcher;//for regex
import java.util.regex.Pattern;//for regex
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // For removing title bar
        setContentView(R.layout.activity_main);

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnShow   = (Button)findViewById(R.id.btnShow);
        final EditText tEmail   = (EditText)findViewById(R.id.txtEmail);
        final EditText tPw   = (EditText)findViewById(R.id.txtPw);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


//start of Validations


                String msg;


                String strEmail = tEmail.getText().toString();
                String strPw = tPw.getText().toString();

                if (strEmail.equals("")){
                    tEmail.setError( "Empty Field, Please Enter your Email Address");
                }
                if (strPw.equals("")){
                    tPw.setError( "Empty Field, Please Enter your Password");
                }




                if (!strEmail.equals("") && !strPw.equals("")){
                    if (isEmailValid(strEmail)==true && strPw.length() >= 8 && strPw.matches(".*\\d+.*")){
                        if (strEmail.equals("a@a.com") && strPw.equals("12345Bla")) {

                            Toast.makeText(getBaseContext(),"Logged-In Successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,NextScreen.class );
                            startActivity(intent);
                            finish();

                        }else{


                            Toast.makeText(getBaseContext(),"Log-in Failed,Invalid password or Account Does not exist",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        if (isEmailValid(strEmail)!=true) {
                            tEmail.setError("Log-in Failed, Invalid email address");
                        }

                        if (strPw.length()<8 || strPw.matches(".*\\d+.*") == false) {
                            tPw.setError("Log-in Failed,Password must contain at least 8 characters and at least 1 digit");
                        }
                    }
                }

//end of Validations




            }
        });


        btnShow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int eventP = event.getAction();
                final int cursor = tPw.getSelectionStart();
                switch (eventP){
                    case MotionEvent.ACTION_DOWN:
                        tPw.setTransformationMethod(null);
                        tPw.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP :
                        tPw.setTransformationMethod(new PasswordTransformationMethod());
                        tPw.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        tPw.setTransformationMethod(new PasswordTransformationMethod());
                        tPw.setSelection(cursor);
                        break;
                }






                return true;

            }

        });



    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }








}
