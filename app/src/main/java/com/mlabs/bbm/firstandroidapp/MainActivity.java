package com.mlabs.bbm.firstandroidapp;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import  android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity {


    EditText Uname;
    EditText Pwd;
    Button btnLogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


