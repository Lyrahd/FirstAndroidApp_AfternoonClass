package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Ruzzl on 7/22/2016.
 */
public class login extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginbtn;
        final EditText emailAddress, passW;

        emailAddress = (EditText)findViewById(R.id.editText);
        passW = (EditText)findViewById(R.id.editText2);
        loginbtn = (Button)findViewById(R.id.button);
        if (loginbtn != null) {
            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(emailAddress.getText()).matches()){
                        if(!(passW.length()== 0)){
                            if(passW.length()>8){
                                Intent intent = new Intent(login.this,MainActivity.class );
                                startActivity(intent);
                            } else Toast.makeText(getBaseContext(),"Password is too short", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(),"Password field is empty", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getBaseContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();


                    /*Toast.makeText(getBaseContext(),, Toast.LENGTH_SHORT).show();*/
                }
            });

        }

    }
}
