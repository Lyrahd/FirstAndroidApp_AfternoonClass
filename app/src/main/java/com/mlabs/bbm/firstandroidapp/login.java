package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


/**
 * Created by Tim on 7/21/2016.
 */

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button Login;
        final EditText txtEmail;
        final EditText txtPass;
        txtPass = (EditText)findViewById((R.id.txtPass));
        txtEmail = (EditText)findViewById((R.id.txtEmail));
        Login = (Button)findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(txtEmail.getText()).matches())
                    {
                        if (txtPass.length()>=8)
                        {
                            Intent intent = new Intent(login.this,afterlogin.class );
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"Password must be at least 8 characters.", Toast.LENGTH_SHORT).show();
                        }
                    }
                else
                    {
                        Toast.makeText(getBaseContext(),"Please enter a valid e-mail address.", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        Button pShow;
        pShow = (Button)findViewById(R.id.btnShow);
        pShow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                /**
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                 case MotionEvent.ACTION_CANCEL:
                 txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                 break;
                }
                 **/
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else if (event.getAction()==MotionEvent.ACTION_UP){
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else if (event.getAction()==MotionEvent.ACTION_CANCEL){
                    txtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                return true;
            }
        });
    }
    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}

