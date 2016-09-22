package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        SQLiteDatabase mydatabase = openOrCreateDatabase("dbAccounts",MODE_PRIVATE,null);
        final DBHelper mydb = new DBHelper(this) ;
        show = (Button)findViewById(R.id.show);
        Button signup;
        signup= (Button)findViewById((R.id.button2));
        if (btn!=null){
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){

                    if(Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(emailAdd.getText()).matches()){
                        if(!(passWord.length()== 0)){
                            if(passWord.length()>8){
                                if (mydb.validateUser(emailAdd.getText().toString(), passWord.getText().toString())=="True") {
                                    Intent intent = new Intent(MainActivity.this, blankAct.class);
                                    startActivity(intent);
                                    //for disposing
                                    finish();
                                }else Toast.makeText(getBaseContext(),"Incorrect email or password", Toast.LENGTH_SHORT).show();
                            } else Toast.makeText(getBaseContext(),"Password too short", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(),"Password field is empty", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getBaseContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();


                }
            });
        }



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,signup.class );
                startActivity(intent);
            }
        });
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
