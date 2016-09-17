package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPActivity extends Activity
{

    boolean emailValidator(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    boolean passwordValidator(String password) {
        if(password.length() > 7) {
            return true;
        } else {
            return false;
        }
    }

    EditText editTextEmail, editTextPassword, editTextConfirmPassword;
    Button btnOk;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        editTextEmail =(EditText)findViewById(R.id.email);
        editTextPassword=(EditText)findViewById(R.id.password);
        editTextConfirmPassword=(EditText)findViewById(R.id.confirm_password);
        btnOk =(Button)findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String email= editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();


                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill out all the field.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(emailValidator(editTextEmail.getText())==false){
                    Toast.makeText(getApplicationContext(), "Invalid Email address.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    if(emailValidator(editTextEmail.getText()) == true &&
                            passwordValidator(editTextPassword.getText().toString()) == true) {
                        loginDataBaseAdapter.insertEntry(email, password);
                        Toast.makeText(getApplicationContext(), "Account Successfully Created.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    else if(emailValidator(editTextEmail.getText()) == true &&
                            passwordValidator(editTextPassword.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(), "Password must be at least 8 character.", Toast.LENGTH_LONG).show();
                        return;
                    }
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
