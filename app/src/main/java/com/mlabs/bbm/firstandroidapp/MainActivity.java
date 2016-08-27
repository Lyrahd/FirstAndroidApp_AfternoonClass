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

    public static boolean validateEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button btn1,btn2;
    EditText edtxt1,edtxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btnlogin);
        btn2 = (Button) findViewById(R.id.btnshow);
        edtxt1 = (EditText) findViewById(R.id.txUsername);
        edtxt2 = (EditText) findViewById(R.id.txPassword);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtxt1.setError(null);
                edtxt2.setError(null);

                if (validateEmail(edtxt1.getText()) == true && edtxt2.getText().length() > 0) {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                    finish();
                }
                if (validateEmail(edtxt1.getText()) == false) {
                    edtxt1.setError(getString(R.string.Invalid_Email));
                } else if (edtxt1.getText().toString().equals("") &&

                        edtxt2.getText().toString().equals("")) {
                    edtxt1.setError(getString(R.string.error_field_required));
                    edtxt2.setError(getString(R.string.error_field_required));
                } else if (edtxt1.getText().toString().equals("")) {
                    edtxt1.setError(getString(R.string.error_field_required));

                } else if (edtxt2.getText().toString().equals("")) {
                    edtxt2.setError(getString(R.string.error_field_required));
                }

            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int event = motionEvent.getAction();
                final int cursor = edtxt2.getSelectionStart();
                switch (event)
                {
                    case MotionEvent.ACTION_UP:
                        edtxt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        edtxt2.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        edtxt2.setTransformationMethod(null);
                        edtxt2.setSelection(cursor);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        edtxt2.setTransformationMethod(null);
                        edtxt2.setSelection(cursor);
                        return true;
                }
                return true;
                /**if (event == motionEvent.ACTION_DOWN)
                 {
                 edtxt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                 }
                 else if(event == motionEvent.ACTION_UP)
                 {
                 edtxt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                 }
                 else if(event == motionEvent.ACTION_CANCEL)
                 {
                 edtxt2.setTransformationMethod(null);
                 }**/
            }

        });

    }
}