package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;


public class ontouchlistener extends Activity {
    public ImageView rc;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (ImageView) findViewById(R.id.rc);


        rc.setOnTouchListener(new View.OnTouchListener() {

            float x1, x2;
            float y1, y2;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        Toast.makeText(getBaseContext(), "x1=" + x1 + "y1=" + y1, Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        x2 = motionEvent.getX();
                        y2 = motionEvent.getX();

                        if (x1 > x2) {
                            Toast.makeText(getBaseContext(), "SWIPE LEFT. x2=" + x2 + "y2=" + y2, Toast.LENGTH_SHORT).show();
                        }
                        if (x1 < x2) {
                            Toast.makeText(getBaseContext(), "SWIPE RIGHT. x2=" + x2 + "y2=" + y2, Toast.LENGTH_SHORT).show();
                        }
                        if (y1 < y2) {
                            Toast.makeText(getBaseContext(), "SWIPE DOWN. x2=" + x2 + "y2=" + y2, Toast.LENGTH_SHORT).show();
                        }
                        if (y1 > y2) {
                            Toast.makeText(getBaseContext(), "SWIPE UP. x2=" + x2 + "y2=" + y2, Toast.LENGTH_SHORT).show();
                        }
                        break;

                    }

                }
                return true;
            }

        });
    }


}



