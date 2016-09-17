package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static boolean Email_Validate(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button login_btn,show_btn;
    EditText email_txt,password_txt;
    TextView signup;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn=(Button)findViewById(R.id.login_btn);
        email_txt=(EditText)findViewById(R.id.email_txt);
        password_txt=(EditText)findViewById(R.id.password_txt);
        show_btn=(Button)findViewById(R.id.show_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_txt.setError(null);
                password_txt.setError(null);

                if(Email_Validate(email_txt.getText())==true && password_txt.getText().length()>0)
                {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                    finish();
                    //hello
                }

                if(Email_Validate(email_txt.getText())==false)
                {
                    email_txt.setError(getString(R.string.error_email));
                }


                else if(email_txt.getText().toString().equals("") &&

                        password_txt.getText().toString().equals("")) {
                    email_txt.setError(getString(R.string.error_field_required));
                    password_txt.setError(getString(R.string.error_field_required));
                }

                else if(email_txt.getText().toString().equals("")) {
                    email_txt.setError(getString(R.string.error_field_required));

                }

                else if(password_txt.getText().toString().equals("")) {
                    password_txt.setError(getString(R.string.error_field_required));
                }

            }
        });
        
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    show_btn.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int event = motionEvent.getAction();
            final int cursor=password_txt.getSelectionStart();

            switch (event) {
                case MotionEvent.ACTION_DOWN:
                    password_txt.setTransformationMethod(null);
                    password_txt.setSelection(cursor);
                    return true;
                case MotionEvent.ACTION_CANCEL:
                    password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password_txt.setSelection(cursor);
                    return true;
                case MotionEvent.ACTION_UP:
                    password_txt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password_txt.setSelection(cursor);
                    return true;
            }
        return true;
        }



        });

    }


}
