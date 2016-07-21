package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText mail, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login =  (Button)findViewById(R.id.button_login);
        mail = (EditText)findViewById(R.id.editText_email);
        pwd = (EditText)findViewById(R.id.editText_pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    if (mail.getText().toString().equals("admin@yahoo.com") && pwd.getText().toString().equals("orayt")){
                        Toast.makeText(getApplicationContext(),"Credentials matched. Redirecting...", Toast.LENGTH_LONG).show();
                        new Thread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                try
                                {
                                    Thread.sleep(3000);
                                    Intent intent_wel = new Intent(MainActivity.this, Welcome.class);
                                    startActivity(intent_wel);
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Credentials do not match..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public boolean check(){
        boolean valid = true;

        String _mail = mail.getText().toString();
        String _pwd = pwd.getText().toString();

        if (_mail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(_mail).matches()){
            Toast.makeText(getApplicationContext(),"Enter valid email",Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (_pwd.isEmpty()){
            Toast.makeText(getApplicationContext(),"Password field is empty",Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }
}
