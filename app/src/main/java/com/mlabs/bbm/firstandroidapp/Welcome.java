package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Keifer on 7/21/2016.
 */
public class Welcome extends Activity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        iv =  (ImageView)findViewById(R.id.imageView);
        iv.setOnTouchListener(new View.OnTouchListener() {
            float x1, x2;
            float y1, y2;
                                @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                    String direct = "";
                                    switch (event.getAction() ) {
                                        case MotionEvent.ACTION_DOWN:
                                            x1 = event.getX(); y1 = event.getY();
                                            Toast.makeText(getApplicationContext(),"X: " + x1 + ", Y: "+y1,Toast.LENGTH_SHORT).show();
                                            return true;
                                        case MotionEvent.ACTION_UP:
                                            x2 = event.getX(); y2 = event.getY();
                                            if (x1 < x2) {
                                                direct = "Swipe right";
                                                Toast.makeText(getApplicationContext(),direct + ", X: " + x2 + ", Y: "+y2,Toast.LENGTH_SHORT).show();
                                            }
                                            if (x1 > x2) {
                                                direct = "Swipe left";
                                                Toast.makeText(getApplicationContext(),direct + ", X: " + x2 + ", Y: "+y2,Toast.LENGTH_SHORT).show();

                                            }
                                            if (y1 > y2) {
                                                direct = "Swipe up";
                                                Toast.makeText(getApplicationContext(),direct + ", X: " + x2 + ", Y: "+y2,Toast.LENGTH_SHORT).show();

                                            }
                                            if (y1 < y2) {
                                                direct = "Swipe down";
                                                Toast.makeText(getApplicationContext(),direct + ", X: " + x2 + ", Y: "+y2,Toast.LENGTH_SHORT).show();

                                            }
                                            break;
                                    }
                                    return false;
                                }
                            }
        );
    }
}
