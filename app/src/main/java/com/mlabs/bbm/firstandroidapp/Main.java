package com.mlabs.bbm.firstandroidapp;

import android.app.usage.UsageEvents;
import android.drm.DrmStore;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by androidstudio on 03/09/16.
 */
public class Main extends android.app.Activity {
    float x, y , x1, y1;
    ImageView imageLogo;
    String k="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainl);

        imageLogo = (ImageView) findViewById(R.id.imageLogo);
        imageLogo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();


                        Toast.makeText(getApplicationContext(), "x = "+x + "  "+"y = "+y, Toast.LENGTH_SHORT).show();



                    case MotionEvent.ACTION_UP:
                        x1 = event.getX();
                        y1= event.getY();

                        if (y < y1 ) {
                           Toast.makeText(getApplicationContext(), "TOP to BOTTOM  x = "+x + "  "+"y = "+y, Toast.LENGTH_SHORT).show();
                        }
                        if(y > y1 ){

                            Toast.makeText(getApplicationContext(), "BOTTOM to UP  x = "+x + "  "+"y = "+y, Toast.LENGTH_SHORT).show();
                            }
                        if (x > x1 ){

                            Toast.makeText(getApplicationContext(), "RIGHT to LEFT  x = "+x + "  "+"y = "+y, Toast.LENGTH_SHORT).show();
                        }

                        if (x < x1 ) {

                            Toast.makeText(getApplicationContext(), "LEFT to RIGHT  x = "+x + "  "+"y = "+y, Toast.LENGTH_SHORT).show();
                        }



                }


                return true;

            }
        });

    }




}

