package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Touch extends AppCompatActivity {

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);

        final ImageView imgTouch = (ImageView) findViewById(R.id.imgTouch);

        imgTouch.setOnTouchListener(new View.OnTouchListener(){

            float iX,iY,fX,fY;
            String mess = "";

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent)
            {
                switch(motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        iX = motionEvent.getX();
                        iY = motionEvent.getY();

                        mess = "X:" +  iX + ", Y:" +  iY;
                        popToast.setText(mess);
                        popToast.show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        fX = motionEvent.getX();
                        fY = motionEvent.getY();
                        if(iX>fX && iY>fY)
                        {
                            mess = "Swiped Left X: " +  fX + ", Swiped Up Y:" +  fY;
                        }
                        if(iX<fX && iY>fY)
                        {
                            mess = "Swiped Right X: " +  fX + ", Swiped Up Y:" +  fY;
                        }
                        if(iX>fX && iY<fY)
                        {
                            mess = "Swiped Left X: " +  fX + ", Swiped Down Y:" +  fY;
                        }
                        if(iX<fX && iY<fY)
                        {
                            mess = "Swiped Right X: " +  fX + ", Swiped Down Y:" +  fY;
                        }
                        popToast.setText(mess);
                        popToast.show();
                        return true;
                    default:
                        return true;
                }

            }
        });
    }


}
