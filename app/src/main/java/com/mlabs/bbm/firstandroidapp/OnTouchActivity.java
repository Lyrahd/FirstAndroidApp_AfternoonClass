package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, finalX = 0, initY = 0, finalY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("ACTION_DOWN - x:%.2f, y:%.2f",initX,initY),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        actionDisplay(initX, finalX, initY, finalY);
                        return true;
                }
                return false;
            }

        });

    }
    public void actionDisplay(float initX, float finalX, float initY, float finalY){

        if (initX<finalX){
            Toast.makeText(OnTouchActivity.this, ("Swiped Left to Right"),
                    Toast.LENGTH_SHORT).show();
        }

        else if(initX>finalX){
            Toast.makeText(OnTouchActivity.this, ("Swiped Right to Left"),
                    Toast.LENGTH_SHORT).show();
        }

        if(initY<finalY){
            Toast.makeText(OnTouchActivity.this, ("Swiped Up to Down"),
                    Toast.LENGTH_SHORT).show();
        }

        else if(initY>finalY){
            Toast.makeText(OnTouchActivity.this, ("Swiped Down to Up"),
                    Toast.LENGTH_SHORT).show();
        }

    }
}