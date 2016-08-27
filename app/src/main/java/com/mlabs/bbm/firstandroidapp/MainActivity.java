package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    public static boolean input_validation(CharSequence email) {
        if (TextUtils.isEmpty(email))
        {
            return false;
        } else

        {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    Button btn1;
    EditText edtxt1,edtxt2;
    Button show;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.login_button);
        edtxt1=(EditText)findViewById(R.id.email_textbox);
        edtxt2=(EditText)findViewById(R.id.password_textbox);
        show = (Button)findViewById(R.id.show);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtxt1.setError(null);
                edtxt2.setError(null);

                if(input_validation(edtxt1.getText())==true && edtxt2.getText().length()>0)
                {
                    Intent i;
                    i = new Intent(MainActivity.this, Activity.class);
                    startActivity(i);
                }

                if(input_validation(edtxt1.getText())==false)
                {
                    edtxt1.setError(getString(R.string.email_error));
                }

                else if(edtxt1.getText().toString().equals("") && edtxt2.getText().toString().equals(""))
                {
                    edtxt1.setError(getString(R.string.fieldreq_error));
                    edtxt2.setError(getString(R.string.fieldreq_error));
                }

                else if(edtxt1.getText().toString().equals(""))
                {
                    edtxt1.setError(getString(R.string.fieldreq_error));

                }

                else if(edtxt2.getText().toString().equals("")) {
                    edtxt2.setError(getString(R.string.fieldreq_error));
                }

            }
        });

        show.setOnTouchListener(new View.OnTouchListener(){
                @Override
            public boolean onTouch(View v, MotionEvent event){
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        edtxt2.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:

                        edtxt2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });

    }


}