package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class Activity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btn_ontouch = (Button) findViewById(R.id.btn_ontouch);
        btn_ontouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(Activity2.this, OnTouchActivity.class);
                startActivity(i);
            }
        });

    }

}