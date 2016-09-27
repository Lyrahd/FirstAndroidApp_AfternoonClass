package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class registeractivity extends AppCompatActivity {
    private loginactivity loginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_acc);

        final DataBaseAdapter sqlDB = new DataBaseAdapter(getApplicationContext());

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText uname = (EditText) findViewById(R.id.uname);
        final EditText firstname = (EditText) findViewById(R.id.firstname);
        final EditText lastname = (EditText) findViewById(R.id.lastname);
        final EditText confirmpass = (EditText) findViewById(R.id.confirmpass);

        final Button btn_reg = (Button) findViewById(R.id.btn_reg);

        final String emailInput = email.getText().toString().trim();
        final String passwordInput = password.getText().toString().trim();
        final String passwordInputVerify = confirmpass.getText().toString().trim();
        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


        //EmailMatcher = "([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com";
        // PassMatcher = "([a-zA-Z0-9])";


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getApplicationContext().toString(), "CLICK");
                if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.([a-zA-Z0-9])+(\\.([a-zA-Z0-9])+)?").matcher(email.getText()).matches() && (Pattern.compile("([a-zA-Z0-9]+)").matcher(password.getText()).matches()) && password.getText().length() >= 8) {
                    Log.d(getApplicationContext().toString(), "PAU");
                    if (password.getText().toString().equals(confirmpass.getText().toString())) {
                        Log.d(registeractivity.this.toString(), "Signing Up..");
                        sqlDB.registerUser( email.getText().toString().trim(),password.getText().toString().trim(), firstname.getText().toString().trim(), lastname.getText().toString().trim(), uname.getText().toString().trim(), getDate());
                        Log.d(registeractivity.class.getSimpleName(),email.getText().toString().trim());
                        Toast.makeText(getApplicationContext(), "User successfully added", Toast.LENGTH_LONG).show();
                        Intent goBackToLoginScreen = new Intent(registeractivity.this, loginactivity.class);
                        startActivity(goBackToLoginScreen);
                    } else {
//hi
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
                }

            }


        });
//hi
    }
    protected void onPause() {
        super.onPause();
        finish();
    }

    public String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }
}