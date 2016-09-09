package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jill Bustillo on 9/9/2016.
 */
public class OnTouchActivity  extends AppCompatActivity {
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ontouch);

        image1 = (ImageView)findViewById(R.id.spoilerlogo1);


        image1.setOnTouchListener(new View.OnTouchListener() {
            float x1, y1, x2, y2;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch(motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        x1=motionEvent.getX();
                        y1=motionEvent.getY();
                        Toast.makeText(getApplicationContext(),"x = "+ x1 + " y = " +y1, Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2=motionEvent.getX();
                        y2=motionEvent.getY();
                        if (x1 < x2) {
                            Toast.makeText(getApplicationContext(), "left to right" + " X = " + x2 + " Y = " + y2, Toast.LENGTH_SHORT).show();
                        }
                        if (x1 > x2) {
                            Toast.makeText(getApplicationContext(),"right to left" +  " X = " + x2 + " Y = " + y2, Toast.LENGTH_SHORT).show();
                        }

                        if (y1 < y2) {
                            Toast.makeText(getApplicationContext(),"top to bottom" + " X = " + x2 + " Y = " + y2, Toast.LENGTH_SHORT).show();
                        }
                        if (y1 > y2) {
                            Toast.makeText(getApplicationContext(),"bottom to top" + " X = " + x2 + " Y = " + y2, Toast.LENGTH_SHORT).show();
                        }
                        return true;
                }
                return true;
            }
        });

    }
}
