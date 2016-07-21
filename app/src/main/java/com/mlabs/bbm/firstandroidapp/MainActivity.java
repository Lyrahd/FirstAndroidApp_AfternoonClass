package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.pass);
        final Button login = (Button) findViewById(R.id.login);

        popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);


        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isValidEmail(email.getText())==false)
                {
                    email.setError("Please enter a valid email address.");
                }
                else if(pass.getText().length()<8)
                {
                    pass.setError("Minimum password length is at least 8 characters.");
                }
                else
                {
                    popToast.setText("Logged in!");
                    popToast.show();
                }
            }

        });



    }
}
