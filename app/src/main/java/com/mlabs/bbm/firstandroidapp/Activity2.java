package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by androidstudio on 03/09/16.
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        final ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, finalX = 0, initY = 0, finalY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("ACTION_DOWN - x:%.2f, y:%.2f",initX,initY),Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        actionDisplay(initX, finalX, initY, finalY);
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        return false;
                }
                return false;
            }

        });

    }
    public void actionDisplay(float initX, float finalX, float initY, float finalY){
        String msg = "";

        if (initX<finalX){
            msg = String.format("SWIPED LEFT to RIGHT");
        }
        if(initX>finalX){
            msg = String.format("SWIPED RIGHT to LEFT");
        }
        Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_SHORT).show();

        if(initY<finalY){
            msg = String.format("SWIPED UP to DOWN");
        }
        if(initY>finalY){
            msg = String.format("SWIPED DOWN to UP");
        }

        Toast.makeText(getApplicationContext(),""+msg,Toast.LENGTH_SHORT).show();
    }
}
