package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Viljo on 7/22/2016.
 */
public class WelcomeScreen extends Activity {
    public ImageView touchmobebemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        touchmobebemo = (ImageView) findViewById(R.id.touchmobebemo);

        touchmobebemo.setOnTouchListener(new View.OnTouchListener() {
            float x1,x2;
            float y1,y2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        Toast.makeText(getApplicationContext(), " X1 ="+ x1 + " Y1 =" + y1, Toast.LENGTH_SHORT).show();
                        break;

                    case MotionEvent.ACTION_UP:
                                                         x2 = event.getX();
                                                         y2 = event.getY();

                                                         if (x1 > x2){
                                                             Toast.makeText(getApplicationContext(), "Swipe Left. X2 ="+ x2 + " Y2 =" + y2, Toast.LENGTH_SHORT).show();
                                                         }
                                                         if (x1 < x2){
                                                             Toast.makeText(getApplicationContext(), "Swipe Right. X2 ="+ x2 + " Y2 =" + y2, Toast.LENGTH_SHORT).show();
                                                         }
                                                         if (y1 > y2){
                                                         Toast.makeText(getApplicationContext(), "Swipe Up. X2 ="+ x2 + " Y2 =" + y2, Toast.LENGTH_SHORT).show();
                                                         }
                                                         if (y1 < y2){
                                                             Toast.makeText(getApplicationContext(), "Swipe Down. X2 ="+ x2 + " Y2 =" + y2, Toast.LENGTH_SHORT).show();
                                                         }
                                                         break;

                                                 }
                                                 return true;
                                             }
        }
        );


    }
}


