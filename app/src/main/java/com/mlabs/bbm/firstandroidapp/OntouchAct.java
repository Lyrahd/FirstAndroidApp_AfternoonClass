package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by androidstudio on 03/09/16.
 */
public class OntouchAct extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontouch);
        final ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, finalX = 0, initY = 0, finalY = 0;
            String w, w1;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(), "" + String.format("[x:%,2f][y:%.2f]", motionEvent.getX(), motionEvent.getY()), Toast.LENGTH_LONG).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        if (initX < finalX){
                            w = "Swiped to Right";}
                        else {
                            w = "Swiped to Left";}
                        if (initY < finalY) {
                            w1 ="Swiped Down";}
                        else {
                            w1 = "Swiped Up";}
                        Toast.makeText(getApplicationContext(),w+" "+w1,Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    }



