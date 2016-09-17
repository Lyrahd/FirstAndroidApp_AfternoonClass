package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    Button onTouchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onTouchbtn=(Button) findViewById(R.id.onTouchbtn);

    }

    public void onTouch(View view) {

        Intent intent = new Intent(MainActivity.this, OnTouchActivity.class);
        startActivity(intent);


    }
}


