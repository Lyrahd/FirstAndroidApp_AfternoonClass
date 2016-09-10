package com.mlabs.bbm.firstandroidapp;

/**
 * Created by androidstudio on 03/09/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        final ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setOnTouchListener(new View.OnTouchListener() {
            float initX, finalX, initY, finalY;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){
                int event=motionEvent.getAction();
                switch (event){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("x:%.2f, y:%.2f",initX,initY),Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();



                        if (initX<finalX){
                            Toast.makeText(getApplicationContext(),"SWIPED LEFT to RIGHT",Toast.LENGTH_LONG).show();
                        }
                        if(initX>finalX) {
                            Toast.makeText(getApplicationContext(),"SWIPED RIGHT to LEFT",Toast.LENGTH_LONG).show();
                        }

                        if(initY<finalY){
                            Toast.makeText(getApplicationContext(),"SWIPED UP to DOWN",Toast.LENGTH_LONG).show();
                        }
                        if(initY>finalY){
                            Toast.makeText(getApplicationContext(),"SWIPED DOWN to UP",Toast.LENGTH_LONG).show();
                        }


                        return true;

                }

                return false;

            }


        });

    }

}

