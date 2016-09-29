package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends Activity {
    Button login_btn;
    TextView txtsign;
    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        txtsign = (TextView) findViewById(R.id.txtsignup);
        login_btn = (Button) findViewById(R.id.login_btn);


        txtsign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {

            final EditText editTextEmail = (EditText) findViewById(R.id.email_txt);
            final EditText ediTextPassword = (EditText) findViewById(R.id.password_txt);

            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String password = ediTextPassword.getText().toString();
                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(email);

                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Successfully logged in.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), TouchListener.class);
                    startActivity(intent);

                } else {
                    if (email.equals("") && password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (email.equals("") || password.equals("")) {
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