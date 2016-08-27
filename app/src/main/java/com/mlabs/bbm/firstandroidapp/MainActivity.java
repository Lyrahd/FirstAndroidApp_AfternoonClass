package com.mlabs.bbm.firstandroidapp;

import android.content.DialogInterface;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import  android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends ActionBarActivity {


    EditText Uname;
    EditText Pwd;
    Button btnLogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Uname = (EditText)findViewById(R.id.TFUname);
       // Pwd = (EditText)findViewById(R.id.TFPword);
        //btnLogs = (Button)findViewById(R.id.btnLog);

TextView TV_show = (TextView) findViewById(R.id.TVShow);


        TV_show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                EditText a = (EditText) findViewById(R.id.TFUname);
                EditText p = (EditText) findViewById(R.id.TFPword);
                Intent i = new Intent(MainActivity.this, Display.class);

                String uname = a.getText().toString();
                String pword = p.getText().toString();
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        p.setTransformationMethod(null);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        p.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;

                }
                return false;
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
           //String line = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            //String pattern = "[a-zA-Z]+";
            //Pattern r = Pattern.compile(pattern);


           // Matcher m = r.matcher(line);
           // String x = m.toString();
            boolean b = Pattern.matches("[a-zA-Z0-9]{4,16}", uname);
            boolean c = Pattern.matches("[a-zA-Z0-9]{8,24}",pword);
            if(b == true && c == true)
            {

                i.putExtra("Username",uname);
                startActivity(i);

            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Characters Must be:" +  "\n" +
                        "- Alphanumeric" + "\n" +
                        "- 5 to 16 Characters");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
