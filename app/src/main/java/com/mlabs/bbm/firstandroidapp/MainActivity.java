package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText editext1, editext2;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button);
        editext1=(EditText)findViewById(R.id.editText);
        editext2=(EditText)findViewById(R.id.editText2);






        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editext1.setError(null);
                editext2.setError(null);

                if(editext1.getText().toString().equals("admin") &&

                        editext2.getText().toString().equals("admin")) {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                }

                else if(editext1.getText().toString().equals("") &&

                        editext2.getText().toString().equals("")) {
                    editext1.setError(getString(R.string.error_field_required));
                    editext2.setError(getString(R.string.error_field_required));
                }

                else if(editext1.getText().toString().equals("")) {
                    editext1.setError(getString(R.string.error_field_required));

                }

                else if(editext2.getText().toString().equals("")) {
                    editext2.setError(getString(R.string.error_field_required));
                }

                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    counter--;

                    if (counter == 0) {
                        button1.setEnabled(false);
                    }
                }
            }
        });


    }


}