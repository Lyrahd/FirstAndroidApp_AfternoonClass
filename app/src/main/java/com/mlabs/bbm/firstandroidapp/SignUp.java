package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    EditText editEmail;
    EditText editPassSU;
    EditText editPassCon;
    Button btnRegister;
    DatabaseAdapter loginDatabaseAdapter;

    boolean pwvalidate(String password) {
        if(password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }
    //------------------------------------------------------

    //------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //commit
        loginDatabaseAdapter = new DatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();

        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassSU = (EditText)findViewById(R.id.editPassSU);
        editPassCon = (EditText)findViewById(R.id.editPassCon);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 */
                //if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editEmail.getText()).matches() && editPassSU.length()>=8 && editPassCon.length()>=8 && editPassSU == editPassCon)
                final String emailInput = editEmail.getText().toString().trim();
                final String passwordInput = editPassSU.getText().toString().trim();
                final String passwordInputVerify = editPassCon.getText().toString().trim();

                //-------------------------
                //if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editUser.getText()).matches() && editPass.length()>=6)
                if(emailInput.equals("")||passwordInput.equals("")||passwordInputVerify.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                    return;
                }
                //if(emailValidator(editTextEmail.getText())==false){
                if(Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editEmail.getText()).matches()==false){
                    Toast.makeText(getApplicationContext(), "Invalid Email address.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!passwordInput.equals(passwordInputVerify))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    if(Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editEmail.getText()).matches()==true && pwvalidate(editPassSU.getText().toString())==true){
                        loginDatabaseAdapter.insertEntry(emailInput, passwordInput);
                        Toast.makeText(getApplicationContext(), "Agent Successfully Activated.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editEmail.getText()).matches()==true && pwvalidate(editPassSU.getText().toString())==false){
                        Toast.makeText(getApplicationContext(), "Password must be at least 8 character.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        });
    }
    //---------------------------------------------------------------------------------------------
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}
