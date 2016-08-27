package com.mlabs.bbm.firstandroidapp;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import  android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity {


    EditText Uname;
    EditText Pwd,TFPword;
    Button btnLogs,pshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pshow = (Button) findViewById(R.id.pshow);
        TFPword = (EditText)findViewById(R.id.TFPword);


        pshow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                final int cursor = TFPword.getSelectionStart();
                int eventP = event.getAction();
                switch (eventP) {
                    case MotionEvent.ACTION_UP:
                        Log.d("Classname","ACTION_UP");
                        TFPword.setTransformationMethod(new PasswordTransformationMethod());
                        TFPword.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Classname","ACTION_DOWN");
                        TFPword.setTransformationMethod(null);
                        TFPword.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.d("Classname","ACTION_CANCEL");
                        TFPword.setTransformationMethod(new PasswordTransformationMethod());
                        TFPword.setSelection(cursor);
                        break;
                }
                return true;
            }
        });

    }

    public void onButtonClick(View v)
    {
        if(v.getId() ==  R.id.btnLog)
        {
            EditText a = (EditText)findViewById(R.id.TFUname);
            EditText p = (EditText)findViewById(R.id.TFPword);

            Intent i = new Intent(MainActivity.this, Display.class);

            String uname = a.getText().toString();
            String pword = p.getText().toString();

            boolean b = Pattern.matches("[a-zA-Z0-9]{4,16}", uname);
            if(b == true)
            {

                i.putExtra("Username",uname);
                startActivity(i);
                Intent intent = new Intent(MainActivity.this,Display.class );
                startActivity(intent);
                finish();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(" Invalid Username or Password");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }


        }


    }


    public void clickExit(View x)
    {
        finish();
    }

}


