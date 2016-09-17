package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class Register extends Activity{
    EditText email,pwd1,pwd2;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        email = (EditText)findViewById(R.id.edit_email);
        pwd1 = (EditText)findViewById(R.id.edit_pass1);
        pwd2 = (EditText)findViewById(R.id.edit_pass2);
        reg = (Button)findViewById(R.id.btn_register);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    Toast.makeText(getApplicationContext(),"Registration success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean check(){
        String _email = email.getText().toString();
        String _pwd1 = pwd1.getText().toString();
        String _pwd2 = pwd2.getText().toString();

        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(email_pattern);
        matcher = pattern.matcher(_email);

        if (_email.isEmpty() || !matcher.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_pwd1.isEmpty() || _pwd2.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Password is/are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_pwd1.length() < 8 || _pwd2.length() < 8){
            Toast.makeText(getApplicationContext(),"Password too short. Should be 8 characters or longer", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!_pwd1.equals(_pwd2)) {
            Toast.makeText(getApplicationContext(),"Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
