package com.mlabs.bbm.firstandroidapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ImageView imgs = (ImageView) findViewById(R.id.imgs);

        imgs.setOnTouchListener(new View.OnTouchListener() {
            float inx =0;
            float iny=0;
            float finalx=0;
            float finaly=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int ev = event.getAction();

                switch (ev) {
                    case MotionEvent.ACTION_DOWN:
                        inx = event.getX();
                        iny = event.getY();
                        Toast.makeText(getBaseContext(),"x: "+inx+"y: "+iny,Toast.LENGTH_SHORT).show();
                        break;

                    case MotionEvent.ACTION_UP:
                        finalx = event.getX();
                        finaly = event.getY();
                        //Toast.makeText(getBaseContext(),"x:" + finalx + "y:"+finaly,Toast.LENGTH_SHORT).show();
                        if(inx<finalx){

                            Toast.makeText(getBaseContext(), "Swiped left to right",Toast.LENGTH_SHORT).show();
                        }
                        else if(inx>finalx){
                            Toast.makeText(getBaseContext(),"Swiped right to left",Toast.LENGTH_SHORT).show();
                        }

                        if (iny<finaly){
                            Toast.makeText(getBaseContext(), "Swiped top to down",Toast.LENGTH_SHORT).show();
                        }
                        else if(iny>finaly){
                            Toast.makeText(getBaseContext(), "Swiped down to top",Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
                //return true;
                return true;
            }

        });
    }


    }

