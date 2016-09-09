package com.mlabs.bbm.firstandroidapp;

/**
 * Created by Jasmin Bunani on 7/20/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        final Button btnClick = (Button) findViewById(R.id.ontouch_btn);
        btnClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Activity.this, OnTouchActivity.class);
                startActivity(intent);
            }
        });
    }}
