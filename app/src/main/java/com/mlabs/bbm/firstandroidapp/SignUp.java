package com.mlabs.bbm.firstandroidapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        final EditText remail = (EditText) findViewById(R.id.email);
        final EditText rpass = (EditText) findViewById(R.id.pwd);
        final EditText rconfpass = (EditText) findViewById(R.id.cpwd);
        final Button register = (Button) findViewById(R.id.ok_btn);

        popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isValidEmail(remail.getText())==false)
                {
                    remail.setError("Please enter a valid email address.");
                }
                else if(rpass.getText().length()<8)
                {
                    rpass.setError("Minimum password length is at least 8 characters.");
                }
                else if(rpass.getText().toString().equals(rconfpass.getText().toString()))
                {


                    loginDataBaseAdapter.insertEntry(remail.getText().toString(),rpass.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(SignUp.this,MainActivity.class );
                    startActivity(intent);
                }
                else
                {
                    rconfpass.setError("Passwords does not match");
                }
            }

        });
    }

}
