package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Guitarista on 9/17/2016.
 */
public class SignUp extends AppCompatActivity{
    private EditText editEmail;
    private EditText editPassSU;
    private EditText editPassCon;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //commit
        final DatabaseAdapter sqlDB = new DatabaseAdapter(getApplicationContext());
        final EditText email = (EditText)findViewById(R.id.editEmail);
        final EditText password = (EditText)findViewById(R.id.editPassSU);
        final EditText verifypassword = (EditText)findViewById(R.id.editPassCon);
        final Button btnRegister = (Button)findViewById(R.id.btnLogin);

        final String emailInput = email.getText().toString().trim();
        final String passwordInput = password.getText().toString().trim();
        final String passwordInputVerify = verifypassword.getText().toString().trim();
        final String getCurrentTime = DateFormat.getDateTimeInstance().format(new Date());

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 */
                //if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editEmail.getText()).matches() && editPassSU.length()>=8 && editPassCon.length()>=8 && editPassSU == editPassCon)
                if (!emailInput.equals("") && !passwordInput.equals("") && !passwordInputVerify.equals("")){
                    Log.d(getApplicationContext().toString(),"CLICK");
                    if (passwordInput.equals(passwordInputVerify)){
                        Log.d(SignUp.this.toString(),"SIGNING UP..");
                        sqlDB.registerUser(emailInput, passwordInput, getCurrentTime);
                        Toast.makeText(getApplicationContext(),"User successfully added",Toast.LENGTH_LONG).show();
                        Intent s = new Intent(getApplicationContext(),Display.class);
                        startActivity(s);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"User successfully added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
