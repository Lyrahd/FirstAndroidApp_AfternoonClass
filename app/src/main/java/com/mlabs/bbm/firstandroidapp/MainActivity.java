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

    public static boolean emailChecker(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button btnLogin, show;
    EditText txtEmail,txtPword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPword = (EditText) findViewById(R.id.txtPword);
        show = (Button) findViewById(R.id.show);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmail.setError(null);
                txtPword.setError(null);

                if (emailChecker(txtEmail.getText()) == true && txtPword.getText().length() > 0) {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                    finish(); //change
                }

                if (emailChecker(txtEmail.getText()) == false) {
                    txtEmail.setError(getString(R.string.invalid_email));
                } else if (txtEmail.getText().toString().equals("") && txtPword.getText().toString().equals("")) {
                    txtEmail.setError(getString(R.string.error_field_required));
                    txtPword.setError(getString(R.string.error_field_required));
                } else if (txtEmail.getText().toString().equals("")) {
                    txtEmail.setError(getString(R.string.error_field_required));

                } else if (txtPword.getText().toString().equals("")) {
                    txtPword.setError(getString(R.string.error_field_required));
                }

            }
        });
        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int event = motionEvent.getAction();

                /*if (event == MotionEvent.ACTION_DOWN) {
                    txtPword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (event == MotionEvent.ACTION_UP) {
                    txtPword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else if (event == MotionEvent.ACTION_CANCEL) {
                    txtPword.setTransformationMethod(new PasswordTransformationMethod());
                }*/


                switch (event) {
                    case MotionEvent.ACTION_UP:
                        txtPword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        txtPword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        txtPword.setTransformationMethod(new PasswordTransformationMethod());
                        return true;
                }
                return true;
            }

        });

    }


}