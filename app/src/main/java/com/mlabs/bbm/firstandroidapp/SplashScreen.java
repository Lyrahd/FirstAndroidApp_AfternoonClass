package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by benjarmanalili on 16/07/2016.
 */
public class SplashScreen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
        public void run(){
            try{
                sleep(4000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            finally{
                Intent intent = new Intent(SplashScreen.this,MainActivity.class );
                startActivity(intent);
            }
        }
    };
        timerThread.start();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }
}
