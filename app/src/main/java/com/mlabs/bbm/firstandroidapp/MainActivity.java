package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static boolean validationEm(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button btn1, show_btn;
    EditText txt_mail,txt_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.button);
        txt_mail=(EditText)findViewById(R.id.txt_email);
        txt_pw=(EditText)findViewById(R.id.txt_pw);
        show_btn=(Button)findViewById(R.id.show_btn);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_mail.setError(null);
                txt_pw.setError(null);

                if(validationEm(txt_mail.getText())==true && txt_pw.getText().length()>0)
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