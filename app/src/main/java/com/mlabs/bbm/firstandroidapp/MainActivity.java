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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    public static boolean Email_Validate(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private Toast popToast;
    Button login_btn,show_btn;
    EditText email_txt,password_txt;
    TextView txtsign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn=(Button)findViewById(R.id.login_btn);
        email_txt=(EditText)findViewById(R.id.email_txt);
        password_txt=(EditText)findViewById(R.id.password_txt);
        show_btn=(Button)findViewById(R.id.show_btn);
        txtsign = (TextView) findViewById(R.id.txtsignup);
        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_txt.setError(null);
                password_txt.setError(null);

                if(Email_Validate(email_txt.getText())==true && password_txt.getText().length()>0)
                {
                    Intent i;
                    i = new Intent(MainActivity.this, TouchListener.class);
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

                else {
                    String userName = email_txt.getText().toString();
                    String password = password_txt.getText().toString();

                    // fetch the Password form database for respective user name
                    String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                    // check if the Stored password matches with  Password entered by user
                    if (password.equals(storedPassword)) {
                        Toast.makeText(MainActivity.this, userName + " has logged in. \n Password: " + password, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, TouchListener.class);
                        startActivity(intent);
                    //dialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "User Name or Password is incorrect", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }

            }

        });

        //txtREg keme
        txtsign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,SignUp.class );
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