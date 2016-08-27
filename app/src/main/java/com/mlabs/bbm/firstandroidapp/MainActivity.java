package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    boolean emailValidator(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    boolean passwordValidator(String password) {
        if(password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }

    EditText email;
    EditText password;
    Button button_login;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    public void LoginButton() {
        email = (EditText) findViewById(R.id.txtbox_email);
        password = (EditText) findViewById(R.id.txtbox_pass);
        button_login = (Button) findViewById(R.id.btn_login);
        show = (Button) findViewById(R.id.show);


        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        email.setError(null);
                        password.setError(null);

                        if (email.getText().toString().equals("") &&
                                password.getText().toString().equals(""))
                        {
                            email.setError(getString(R.string.empty_field));
                            password.setError(getString(R.string.empty_field));
                        }

                        else if(emailValidator(email.getText()) == true &&
                                passwordValidator(password.getText().toString()) == true)
                        {
                            Intent i;
                            i = new Intent(MainActivity.this, Activity2.class);
                            startActivity(i);
                            finish();
                            Toast.makeText(MainActivity.this, (R.string.login),
                                    Toast.LENGTH_SHORT).show();
                        }

                        else if (emailValidator(email.getText()) == true &&
                                password.getText().toString().equals(""))
                        {
                            password.setError(getString(R.string.empty_field));
                        }

                        else if (email.getText().toString().equals("") &&
                                passwordValidator(password.getText().toString()) == true)
                        {
                            email.setError(getString(R.string.empty_field));
                        }

                        else if (email.getText().toString().equals("") &&
                                passwordValidator(password.getText().toString()) == false)
                        {
                            email.setError(getString(R.string.empty_field));
                            password.setError(getString(R.string.invalid_password));
                        }

                        else if (emailValidator(email.getText()) == false &&
                                password.getText().toString().equals("") )
                        {
                            email.setError(getString(R.string.invalid_email));
                            password.setError(getString(R.string.empty_field));
                        }

                        else if(emailValidator(email.getText()) == true &&
                                passwordValidator(password.getText().toString()) == false)
                        {
                            password.setError(getString(R.string.invalid_password));
                        }

                        else if(emailValidator(email.getText()) == false &&
                                passwordValidator(password.getText().toString()) == true)
                        {
                            email.setError(getString(R.string.invalid_email));
                        }

                        else if(emailValidator(email.getText()) == false &&
                                passwordValidator(password.getText().toString()) == false)
                        {
                            email.setError(getString(R.string.invalid_email));
                            password.setError(getString(R.string.invalid_password));
                        }

                    }
                }



        );

        show.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
            return true;
            }
        });

    }
}