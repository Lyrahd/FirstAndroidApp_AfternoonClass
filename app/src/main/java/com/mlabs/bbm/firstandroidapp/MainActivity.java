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

                if (passWord.equals("") && !emailAdd.equals("")) {
                    Toast.makeText(getBaseContext(),"Email or Password field must not be empty",Toast.LENGTH_SHORT).show();
                }else
                    if (Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$").matcher(emailAdd.getText()).matches()) {
                        if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(passWord.getText()).matches()) {
                            if (passWord.getText().length() >= 6) {
                                Intent intent = new Intent(MainActivity.this, blankAct.class);
                                startActivity(intent);
                                Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else
                                Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getBaseContext(), "Invalid Email address!", Toast.LENGTH_SHORT).show();

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
