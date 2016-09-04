package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Guitarista on 7/22/2016.
 */
public class Display extends Activity{

    MediaPlayer mySound1;   //second variable for sound
    public ImageView ontouch; // variable for ontouch

    @Override
    protected void onPause() {
        super.onPause();
        mySound1.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        mySound1 = MediaPlayer.create(this, R.raw.isac_dz);
        mySound1.start();
        ontouch = (ImageView) findViewById(R.id.ontouch); //another declaration for pwd_show


        ontouch.setOnTouchListener(new View.OnTouchListener(){
            float x1, x2;
            float y1, y2;

            @Override
            public boolean onTouch(View view, MotionEvent motionevent){

                switch ( motionevent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionevent.getX();
                        y1 = motionevent.getY();
                        Toast.makeText(getBaseContext(), "X1= " + x1 + " Y1= " + y1 ,Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = motionevent.getX();
                        y2 = motionevent.getY();

                        if (x1 > x2)
                        {
                            Toast.makeText(getBaseContext(), "SWIPE LEFT. X2= " + x2 + " Y2= " + y2  ,Toast.LENGTH_SHORT).show();
                        }
                        if (x1 < x2)
                        {
                            Toast.makeText(getBaseContext(), "SWIPE RIGHT. X2= " + x2 + " Y2= " + y2  ,Toast.LENGTH_SHORT).show();
                        }
                        if (y1 > y2)
                        {
                            Toast.makeText(getBaseContext(), "SWIPE UP. X2= " + x2 + " Y2= " + y2  ,Toast.LENGTH_SHORT).show();
                        }
                        if (y1 < y2)
                        {
                            Toast.makeText(getBaseContext(), "SWIPE DOWN. X2= " + x2 + " Y2= " + y2  ,Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });
    }
}
