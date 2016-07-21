package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
        popToast.setText("Logged in!");
        popToast.show();
    }

}
