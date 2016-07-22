package com.mlabs.bbm.firstandroidapp;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends Activity {
    EditText NameEditText,wordEditText;

    Button RegistrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        RegistrationButton=(Button)findViewById(R.id.button1);
        NameEditText=(EditText)findViewById(R.id.editText1);
        wordEditText=(EditText)findViewById(R.id.editText2);
        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name=NameEditText.getText().toString();
                final String word=wordEditText.getText().toString();
                if(Name.length()==0)
                {
                    NameEditText.requestFocus();
                    NameEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Name.matches("[a-zA-Z ]+"))
                {
                    NameEditText.requestFocus();
                    NameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(word.length()==0)
                {
                    wordEditText.requestFocus();
                    wordEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

