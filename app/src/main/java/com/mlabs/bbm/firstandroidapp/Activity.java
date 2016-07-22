package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity extends android.app.Activity {
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
