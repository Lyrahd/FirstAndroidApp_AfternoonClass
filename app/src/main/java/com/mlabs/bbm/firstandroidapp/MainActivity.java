package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn;
        final EditText emailAdd,passWord;
        Button show;
        emailAdd=(EditText)findViewById(R.id.editText);
        passWord=(EditText)findViewById(R.id.editText2);
        btn = (Button)findViewById(R.id.button);
        show = (Button)findViewById(R.id.show);
        if (btn!=null){
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    
                if (sqlDB.validateUser(emailAdd.getText().toString(), passWord.getText().toString)){
                    sendMessage("Login Sucessfull", Toast.LENGTH_SHORT);
                    Intent gotoblankAct = new Intent(MainActivity.this,blankAct.class);
                }
                else {
                    sendMessage("Username or Password is incorrect", Toast.LENGTH_SHORT);
                }
                    Intent gotoMainPage = new Intent(MainActivity.this,signup.class);
                    startActivity(gotoMainPage);
                

                }
            });
        }
        show.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    passWord.setTransformationMethod(null);
                    return true;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                return true;
            }
            return  false;
        }

    });
    }


}
