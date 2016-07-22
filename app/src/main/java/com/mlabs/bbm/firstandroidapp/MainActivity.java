package com.mlabs.bbm.firstandroidapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button sign,exit;
    EditText etext,ptext;
    TextView eview,pview,errormsg;
    ImageView logo;
    int ctr = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign=(Button)findViewById(R.id.button);
        exit=(Button)findViewById(R.id.button2);
        etext=(EditText)findViewById(R.id.editText);
        ptext=(EditText)findViewById(R.id.editText2);

        errormsg=(TextView)findViewById(R.id.textView3);
        errormsg.setVisibility(View.GONE);

        sign.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (etext.getText().toString().equals("Veeldyow") && ptext.getText().toString().equals("catiis")) {
                    Toast.makeText(getApplicationContext(), "Redirecting....", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                    errormsg.setVisibility(View.VISIBLE);
                    errormsg.setBackgroundColor(Color.RED);
                    ctr--;
                    errormsg.setText(Integer.toString(ctr));

                    if (ctr == 0) {
                        sign.setEnabled(false);
                    }
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });


    }
}
