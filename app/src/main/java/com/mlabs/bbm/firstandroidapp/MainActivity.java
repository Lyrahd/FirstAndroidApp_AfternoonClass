package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button_login;
    int attempt_counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    public void LoginButton() {
        email = (EditText) findViewById(R.id.txtbox_email);
        password = (EditText) findViewById(R.id.txtbox_pass);
        button_login = (Button) findViewById(R.id.btn_login);


        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        email.setError(null);
                        password.setError(null);

                        if (email.getText().toString().equals("austingueta")&&
                                password.getText().toString().equals("201311217")) {
                            attempt_counter = 3;
                            Intent i;
                            i = new Intent(MainActivity.this, Activity2.class);
                            startActivity(i);
                        }

                        else if (email.getText().toString().equals("") &&
                                password.getText().toString().equals(""))
                        {
                            email.setError(getString(R.string.empty_field));
                            password.setError(getString(R.string.empty_field));
                        }

                        else if (email.getText().toString().equals(""))
                        {
                            email.setError(getString(R.string.empty_field));
                        }

                        else if (password.getText().toString().equals(""))
                        {
                            password.setError(getString(R.string.empty_field));
                        }

                        else {
                            Toast.makeText(MainActivity.this, (R.string.incorrect_password),
                                    Toast.LENGTH_SHORT).show();
                            attempt_counter--;
                            if (attempt_counter == 0)
                                finish();
                        }
                    }
                }
        );
    }

}
