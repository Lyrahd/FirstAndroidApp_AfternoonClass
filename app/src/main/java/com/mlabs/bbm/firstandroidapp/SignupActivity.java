package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
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

public class SignupActivity extends Activity {

    EditText passTxt,emailTxt,confirmpassTxt,Fnametxt,Lnametxt,Usertxt;
    String email,pwd,confirmpwd,fname,lname,uname;
    Button showbtn01,showbtn12,okbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        okbtn=(Button) findViewById(R.id.btnOK);
        showbtn01 = (Button) findViewById(R.id.showbtn1);
        showbtn12 = (Button) findViewById(R.id.showbtn2);
        passTxt = (EditText) findViewById(R.id.passText);
        emailTxt = (EditText) findViewById(R.id.emailText);
        confirmpassTxt = (EditText) findViewById(R.id.confirmText);
        Fnametxt=(EditText)findViewById(R.id.Fname);
        Lnametxt=(EditText)findViewById(R.id.Lnametxt);
        Usertxt=(EditText)findViewById(R.id.Usertxt);
        Fnametxt.requestFocus();
        showbtn01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //int event = motionEvent.getAction();
                final int cursor = confirmpassTxt.getSelectionStart();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname", "ACTION_DOWN");
                        confirmpassTxt.setTransformationMethod(null);
                        confirmpassTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname", "ACTION_UP");
                        confirmpassTxt.setTransformationMethod(new PasswordTransformationMethod());
                        confirmpassTxt.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        confirmpassTxt.setSelection(cursor);
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
        showbtn12.setOnTouchListener(new View.OnTouchListener() {
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





    }


    public void register(View view) {
        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());
        email = emailTxt.getText().toString().trim();
        pwd = passTxt.getText().toString().trim();
        confirmpwd = confirmpassTxt.getText().toString().trim();
        fname = Fnametxt.getText().toString().trim();
        lname = Lnametxt.getText().toString().trim();
        uname = Usertxt.getText().toString().trim();

        if (!email.isEmpty() && !pwd.isEmpty() && !confirmpwd.isEmpty() && !fname.isEmpty() && !lname.isEmpty() && !uname.isEmpty()) {
            if (!validateEmail(uname) && validateEmail(email) && validatePwd(pwd)) {
                // Validating username if it already exist
                if ((sqlDB.validateuname(Usertxt.getText().toString()))||(sqlDB.validateemail(emailTxt.getText().toString()))) {
                    if (sqlDB.validateuname(Usertxt.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Username already exist.", Toast.LENGTH_LONG).show();
                        Usertxt.requestFocus();
                    }
                    if (sqlDB.validateemail(emailTxt.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Email address already exist.", Toast.LENGTH_LONG).show();
                        emailTxt.requestFocus();
                    }
                }else if((!sqlDB.validateuname(Usertxt.getText().toString()))&&(!sqlDB.validateemail(emailTxt.getText().toString()))){
                    if (pwd.equals(confirmpwd)) {

                        Log.d(SignupActivity.this.toString(), "Signing up...");
                        String msg=sqlDB.registerUser(fname, lname, uname, email, pwd);
                        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignupActivity.this, LogIn.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_LONG).show();
                        passTxt.setText("");
                        confirmpassTxt.setText("");
                        confirmpassTxt.requestFocus();
                    }
                }



            } else {

                if (validateEmail(uname)) {
                    Usertxt.setError("Invalid username format");
                    Usertxt.requestFocus();
                }
                if (!validateEmail(email)) {
                    emailTxt.setError("Invalid Email");
                    emailTxt.requestFocus();
                }
                if (!validatePwd(pwd)) {
                    passTxt.setError("Atleast 8 characters");
                    passTxt.requestFocus();

                }
                Toast.makeText(getApplicationContext(), "User cannot be added.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please fill up all fields", Toast.LENGTH_LONG).show();
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


