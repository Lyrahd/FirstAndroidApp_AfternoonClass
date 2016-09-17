package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by androidstudio on 03/09/2016.
 */
public class On_Touch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_touch);

        final ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setOnTouchListener(new View.OnTouchListener() {
            float intX = 0, intY = 0, intX1 = 0, intY1 = 0;
            String m="",m1="";
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        intX = event.getX();
                        intY = event.getY();
                        Toast.makeText(getApplicationContext(),"x= "+intX +"y= " +intY,Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        intX1 = event.getX();
                        intY1 = event.getY();
                        if (intX>intX1){
                            m="Right to Left";
                        }
                        else {
                            m="Left to Right";
                        }
                        if(intY>intY1)   {
                            m1="Bottom to Top";
                        }
                        else {
                            m1="Top to Bottom" +
                                    "";
                        }

                        Toast.makeText(getApplicationContext(),m + " " + m1 + " x= "+intX1 +"y= " +intY1,Toast.LENGTH_SHORT).show();



            }
                return false;
        }
});
    }
}



