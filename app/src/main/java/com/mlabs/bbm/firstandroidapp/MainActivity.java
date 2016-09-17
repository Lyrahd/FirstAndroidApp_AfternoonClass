package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView Shitoge = (ImageView) findViewById(R.id.ImageView1);


        Shitoge.setOnTouchListener(new View.OnTouchListener() {
            float A = 0;
            float A1 = 0;
            float B = 0;
            float B1 = 0;
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventA = motionEvent.getAction();
                switch (eventA) {
                    case MotionEvent.ACTION_DOWN:
                        A = motionEvent.getX();
                        B = motionEvent.getY();
                        Toast.makeText(MainActivity.this, "Coordinate X: " +A+ " Coordinate Y: "+ B, Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        A1= motionEvent.getX();
                        B1 = motionEvent.getY();
                        if (A > A1){
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +A1+ " Coordinate Y2: "+ B1+ "Swiped lEFT?", Toast.LENGTH_SHORT).show();
                        }
                        if (A < A1){
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +A1+ " Coordinate Y2: "+ B1 + "Swiped rIGHT?", Toast.LENGTH_SHORT).show();
                        }
                        if (B > B1){
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +A1+ " Coordinate Y2: "+ B1  + "Swiped Up?", Toast.LENGTH_SHORT).show();
                        }
                        if (B < B1){
                            Toast.makeText(MainActivity.this, "Coordinate X2: " +A1+ " Coordinate Y2: "+ B1 + "Swiped Down?", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });
    }
}
