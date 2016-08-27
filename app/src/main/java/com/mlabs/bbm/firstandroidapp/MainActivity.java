package com.mlabs.bbm.firstandroidapp;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import  android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity {

    EditText editText_username;
    EditText editText_password;
    Button button_submit;
    Button button_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_username = (EditText) findViewById(R.id.editText_user);
        editText_password = (EditText) findViewById(R.id.editText_password);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_show = (Button) findViewById(R.id.button_show);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUser(editText_username.getText().toString())){
                    editText_username.setError("Invalid Email");
                    editText_username.requestFocus();
                } else if (!validatePassword(editText_password.getText().toString())){
                    editText_password.setError("Invalid Password");
                    editText_password.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Account Validated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,Main_Menu.class );
                    startActivity(intent);
                }
            }

            private boolean validatePassword(String password) {
                if(password!=null && password.length()>6){
                    return true;
                } else {
                    return false;
                }
            }

            private boolean validateUser(String user) {
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(user);

                return matcher.matches();
            }
        });

        button_show.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent show) {
                if (show.getAction() == MotionEvent.ACTION_DOWN){
                    editText_password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if (show.getAction()==MotionEvent.ACTION_UP){
                    editText_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else if (show.getAction()==MotionEvent.ACTION_CANCEL){
                    editText_password.setInputType(InputType.TYPE_CLASS_TEXT)                             ;
                }
                return true;
            }
        });



    }

}


