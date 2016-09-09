package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ClickMeActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickme);

        final ImageView image = (ImageView)findViewById(R.id.imgClickMe);

        assert image != null;
        image.setOnTouchListener(new View.OnTouchListener() {

            float x1, y1, x2, y2;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();

                        Toast.makeText(getApplicationContext(), "x1 = " + x1 + " | y1 = " + y1
                                , Toast.LENGTH_LONG).show();
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();

                        if (x1 < x2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | left to right", Toast.LENGTH_LONG).show();
                        if (x1 > x2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | right to left", Toast.LENGTH_LONG).show();
                        if (y1 < y2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | up to down", Toast.LENGTH_LONG).show();
                        if (y1 > y2)
                            Toast.makeText(getApplicationContext(), "x2 = " + x1 + " | y2 = " + y1 +
                                    " | down to up", Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });

    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}