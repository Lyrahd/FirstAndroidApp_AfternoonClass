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
                setContentView(R.layout.ontouch_activity);

                final ImageView imageTouch = (ImageView) findViewById(R.id.imageTouch);


                imageTouch.setOnTouchListener(new View.OnTouchListener(){
                        float dx,dy,ux,uy;

                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {

                                int x = motionEvent.getAction();

                                switch(x){
                                        case MotionEvent.ACTION_DOWN:
                                                dx = motionEvent.getX();
                                                dy = motionEvent.getY();
                                                Toast.makeText(getApplicationContext(), "Value of X = " + dx + "Y = "+ dy, Toast.LENGTH_LONG).show();
                                                return true;
                                        case MotionEvent.ACTION_UP:
                                                ux = motionEvent.getX();
                                                uy = motionEvent.getY();
                                                if(ux>dx){
                                                        Toast.makeText(getApplicationContext(), "SWIPED RIGHT"+"\n"+"Value of X = " + ux + "Y = "+ uy, Toast.LENGTH_SHORT).show();
                                                }
                                                if(ux<dx){
                                                        Toast.makeText(getApplicationContext(), "SWIPED LEFT"+"\n"+"Value of X = " + ux + "Y = "+ uy, Toast.LENGTH_SHORT).show();
                                                }
                                                if(uy<dy){
                                                        Toast.makeText(getApplicationContext(), "SWIPED UP"+"\n"+"Value of X = " + ux + "Y = "+ uy, Toast.LENGTH_SHORT).show();
                                                }
                                                if(uy>dy) {
                                                        Toast.makeText(getApplicationContext(), "SWIPED DOWN"+"\n"+"Value of X = " + ux + "Y = " + uy, Toast.LENGTH_SHORT).show();
                                                }
                                                return true;
                                }
                                return true;
                        }

                });
        }

        @Override
        protected  void onPause(){
                super.onPause();
                finish();
        }

}


