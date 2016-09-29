package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button btnLogIn;
    TextView btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        btnSignUp = (TextView) findViewById(R.id.SignUp);
        btnLogIn = (Button) findViewById(R.id.btn_login);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {

            final EditText editTextEmail = (EditText) findViewById(R.id.txtbox_email);
            final EditText editTextPassword = (EditText) findViewById(R.id.txtbox_pass);

            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(email);

                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Successfully logged in.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);

                }

                else {
                    if(email.equals("") && password.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else if(email.equals("") || password.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please fill out the field.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "Incorrect Email or Password.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

}