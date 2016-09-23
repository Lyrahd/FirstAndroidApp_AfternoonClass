package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class SignActivity extends AppCompatActivity {

    private int _admin_Id=0;
    DBHelp dbHelp = new DBHelp(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Button buttonok = (Button) findViewById(R.id.btnok);

        final EditText etemail = (EditText) findViewById(R.id.ETun);
        final EditText etpass = (EditText) findViewById(R.id.ETpass);
        final EditText etcp = (EditText) findViewById(R.id.ETcp);

        assert buttonok != null;
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etemail != null && etpass != null) {
                    if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(etemail.getText()).matches()) {
                        if (etpass.length() > 8) {
                            String etpass1 = etpass.getText().toString();
                            String etcp1 = etcp.getText().toString();
                            if (etpass1.equals(etcp1)) {

                                dbHelp.registerUser(String.valueOf(etemail),String.valueOf(etpass));

                                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                                Intent a = new Intent(SignActivity.this, MainActivity.class);
                                startActivity(a);
                                finish();

                            }else{
                                Toast.makeText(getBaseContext(), "Password Does not Match", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Invalid Email address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}