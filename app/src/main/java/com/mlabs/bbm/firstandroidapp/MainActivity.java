package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;



public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText etxt1,etxt2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.login);
        etxt1=(EditText)findViewById(R.id.email_address);
        etxt2=(EditText)findViewById(R.id.pwd);
        final Context context = this;



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etxt1.getText().toString().equals("admin") && etxt2.getText().toString().equals("admin"))
                {

                    Intent intent = new Intent(context, MainForm.class);
                    startActivity(intent);   }

                else
                {
                    Toast.makeText(getApplicationContext(), "The Username/Password is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}


