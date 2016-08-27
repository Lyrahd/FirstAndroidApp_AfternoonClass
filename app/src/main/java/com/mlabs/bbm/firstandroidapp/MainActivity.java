package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.view.View.OnTouchListener;


public class MainActivity extends AppCompatActivity {

    Button btn1, show;
    EditText etxt1,etxt2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.login);
        etxt1=(EditText)findViewById(R.id.email_address);
        etxt2=(EditText)findViewById(R.id.pwd);
        show=(Button)findViewById(R.id.show);
        final Context context = this;



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etxt1.getText().toString().equals("antoniomaryannjane@yahoo.com") && etxt2.getText().toString().equals("admin"))
                {
                    //Toast.makeText(getApplicationContext(), "Loading......",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, MainForm.class);
                    startActivity(intent);
                    etxt1.setText("");
                    etxt2.setText("");

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "The Username/Password is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        etxt2.setTransformationMethod(null);
                     break;

                    case MotionEvent.ACTION_UP:
                        etxt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                     break;

                    //case MotionEvent.ACTION_CANCEL:
                        //etxt2.setTransformationMethod(null);
                        //return true;
                }
                return true;}
        });




}}


