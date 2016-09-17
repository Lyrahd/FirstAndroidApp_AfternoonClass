package com.mlabs.bbm.firstandroidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by androidstudio on 17/09/16.
 */
public class signup extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final EditText email = (EditText)findViewById(R.id.editText3);
        final EditText pwd = (EditText)findViewById(R.id.editText4);
        final EditText cpwd = (EditText)findViewById(R.id.editText5);
        final Button btnSU = (Button)findViewById(R.id.button3);

        final String emailInput = email.getText().toString().trim();
        final String passwordInput = pwd.getText().toString().trim();
        final String passwordInputVerify = cpwd.getText().toString().trim();
        if (btnSU!=null){
            btnSU.setOnClickListener(new View.OnClickListener() {
                @Override
                public  void onClick(View v) {
                if (!emailInput.isEmpty() && !passwordInput.isEmpty() && !passwordInputVerify.isEmpty()) {
                    if(Pattern.compile("[a-zA-Z0-9]+_?+@[a-zA-Z0-9]+\\.com").matcher(emailInput).matches()){
                        }
                        else
                            Toast.makeText(getBaseContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                    if (!(passwordInput.length() == 0)) {
                        if (passwordInput.length() > 8) {
                            Intent intent = new Intent(signup.this, blankAct.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();

                        if (passwordInput.equals(passwordInputVerify)) {
                            sqlDB.registerUser(emailInput, passwordInput, getCurrentDateTime());
                            Toast.makeText(getApplicationContext(), "User Successfully aded", Toast.LENGTH_LONG).show();
                            Intent goBackToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        } else {
                            Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                    else{
                    Toast.makeText(getApplicationContext(), "Please fillup required fields", Toast.LENGTH_SHORT).show();

                    }
                }
                
            });

        }
    }

}
