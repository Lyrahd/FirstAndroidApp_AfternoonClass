package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Guitarista on 7/22/2016.
 */
public class Display extends Activity{

    MediaPlayer mySound1;   //second variable for sound

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

    }
}
