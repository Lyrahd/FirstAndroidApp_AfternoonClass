package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        final EditText emailAdd,pwd;

        emailAdd=(EditText)findViewById(R.id.editText);
        pwd=(EditText)findViewById(R.id.editText2);
        btn = (Button)findViewById(R.id.button);
        if (btn!=null){
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){


                        if (Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$").matcher(emailAdd.getText()).matches() && (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(pwd.getText()).matches() && pwd.getText().length()>=6)) {
                            Intent intent = new Intent(MainActivity.this, blankAct.class);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getBaseContext(), "Login Failed!", Toast.LENGTH_SHORT).show();


                }
            });
        }
    }


}
