package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Villanueva on 7/21/2016.
 */
public class blankAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
        ImageView img;
        img = (ImageView)findViewById(R.id.img);

        img.setOnTouchListener(new View.OnTouchListener() {
            float x=0;
            float y=0;
            float x1=0;
            float y1=0;
            String message="";
            String message1="";
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                         x=e.getX();
                         y=e.getY();
                        Toast.makeText(getBaseContext(),""+"x= "+x+" y= "+y,Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                         x1=e.getX();
                         y1=e.getY();
                            if (x<x1){
                                message="Right To Left";
                            }else{
                                message="Left To Right";
                            }
                            if (y<y1){
                                message1="Top to Bottom";
                            }else
                            {
                                message1="Bottom to Top";
                            }
                        Toast.makeText(getBaseContext(),message+"x= "+x1+" "+message1+" y= "+y1,Toast.LENGTH_SHORT).show();

                }
                return  false;
            }

        });
    }
}
