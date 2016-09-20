package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    public static boolean validationEm(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button btn1, show_btn;
    EditText txt_mail,txt_pw;
    TextView btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.button);
        txt_mail=(EditText)findViewById(R.id.txt_email);
        txt_pw=(EditText)findViewById(R.id.txt_pw);
        show_btn=(Button)findViewById(R.id.show_btn);
        btnSignUp =(TextView)findViewById(R.id.signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
            });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_mail.setError(null);
                txt_pw.setError(null);

                if(validationEm(txt_mail.getText())==true && txt_pw.getText().length()>8)
                {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                }

                if(validationEm(txt_mail.getText())==false)
                {
                    txt_mail.setError(getString(R.string.error_em));
                }


                else if(txt_mail.getText().toString().equals("") &&

                        txt_pw.getText().toString().equals("")) {
                    txt_mail.setError(getString(R.string.error));
                    txt_pw.setError(getString(R.string.error));
                }

                else if(txt_mail.getText().toString().equals("")) {
                    txt_mail.setError(getString(R.string.error));

                }

                else if(txt_pw.getText().toString().equals("")) {
                    txt_pw.setError(getString(R.string.error));
                }
                else

                {  String userName = txt_mail.getText().toString();
                    String password = txt_pw.getText().toString();

                    // fetch the Password form database for respective user name
                    String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                    // check if the Stored password matches with  Password entered by user
                    if (password.equals(storedPassword)) {
                        Toast.makeText(MainActivity.this, userName + " has logged in. \n Password: " + password, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Activity.class );
                        startActivity(intent);

                        //dialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "User Name or Password is incorrect", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
        show_btn.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                int event = motionEvent.getAction();
               /// if (event == MotionEvent.ACTION_DOWN)
                //{
                //    txt_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
              //  }
               // else if (event == MotionEvent.ACTION_UP)
               // {
                  //  txt_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
               // }

               // return false;

                switch(event)
                    {
                        case MotionEvent.ACTION_DOWN:
                        txt_pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                        case MotionEvent.ACTION_UP:
                        txt_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;

                    }
                return true;
            }

        });


    }


}