package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by androidstudio on 17/09/16.
 */
public class SignUp extends AppCompatActivity {

    public static boolean Email_Validate(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button ok;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        ok=(Button)findViewById(R.id.ok_btn);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pwd);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email.setError(null);
                password.setError(null);

                if(Email_Validate(email.getText())==true && password.getText().length()>0)
                {
                    Intent i;
                    i = new Intent(SignUp.this, Activity.class);
                    startActivity(i);
                    finish();
                    //hello
                }

                if(Email_Validate(email.getText())==false)
                {
                    email.setError(getString(R.string.error_email));
                }


                else if(email.getText().toString().equals("") &&

                        password.getText().toString().equals("")) {
                    email.setError(getString(R.string.error_field_required));
                    password.setError(getString(R.string.error_field_required));
                }.

                else if(email.getText().toString().equals("")) {
                    email.setError(getString(R.string.error_field_required));

                }

                else if(password.getText().toString().equals("")) {
                    password.setError(getString(R.string.error_field_required));
                }

            }
        });


    }


}