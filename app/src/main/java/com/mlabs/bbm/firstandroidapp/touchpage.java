package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * Created by androidstudio on 03/09/16.
 */
public class touchpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch);

        final ImageView img=(ImageView) findViewById(R.id.image);

        img.setOnTouchListener(new View.OnTouchListener() {
            float x1, x2,y1,y2;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1=motionEvent.getX();
                        y1=motionEvent.getY();
                        Toast.makeText(getBaseContext(),"X=" +x1 +"and Y="+y1 , Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        x2=motionEvent.getX();
                        y2=motionEvent.getY();

                        if (x1<x2){
                            Toast.makeText(getApplicationContext(), "swipe left to right",Toast.LENGTH_SHORT).show();
                        }
                        if (x1>x2) {
                            Toast.makeText(getApplicationContext(), "swipe right to left" ,Toast.LENGTH_SHORT).show();
                        }
                        if (y1<y2){
                            Toast.makeText(getApplicationContext(),"swipe up to down" ,Toast.LENGTH_SHORT).show();
                        }
                        if (y1>y2) {
                            Toast.makeText(getApplicationContext(), "swipe down to up",Toast.LENGTH_SHORT).show();
                        }



                        return true;
                }
                return true;

            }

            });

}

}
