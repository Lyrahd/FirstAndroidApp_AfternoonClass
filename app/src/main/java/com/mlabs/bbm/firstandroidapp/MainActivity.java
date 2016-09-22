package com.mlabs.bbm.firstandroidapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    TextView btnSignUp,showpass;
    DatabaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new DatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonL);
        btnSignUp=(TextView)findViewById(R.id.signup);
        showpass=(TextView)findViewById(R.id.show);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intentSignUP);
            }
        });
        final  EditText editTextEmail=(EditText)findViewById(R.id.email);
        final  EditText editTextPassword=(EditText)findViewById(R.id.pass);

        Button btnSignIn=(Button)findViewById(R.id.buttonL);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intentSignUP=new Intent(getApplicationContext(),Main2Activity.class);
                    startActivity(intentSignUP);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showpass.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        editTextPassword.setTransformationMethod(null);
                        break;

                    case MotionEvent.ACTION_UP:
                        editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
                        break;
                }
                return true;
            }
        });

    }
    // Methos to handleClick Event of Sign In Button


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}










