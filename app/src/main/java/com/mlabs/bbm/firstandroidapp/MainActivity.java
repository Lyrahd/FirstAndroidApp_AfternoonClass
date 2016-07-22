package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
/**
asdasdasd
 */
    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.btnLogin)
        {
            Intent i = new Intent(MainActivity.this, Display.class);
            startActivity(i);
        }
    }

}

