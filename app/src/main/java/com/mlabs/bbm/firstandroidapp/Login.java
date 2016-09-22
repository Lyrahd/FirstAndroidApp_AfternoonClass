package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RalphPogi on 7/20/2016.
 */
public class Login extends  AppCompatActivity {
    //EditText editText, editText2;
    TextView show, signup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logn);
        Button loginbtn;
        final EditText username = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText2);
        show = (TextView) findViewById(R.id.szhow);
        signup1 = (TextView) findViewById(R.id.signup);
        loginbtn = (Button) findViewById(R.id.button);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAdapter sqlite = new databaseAdapter(getApplicationContext());


               if (!username.getText().toString().equals("") && !password.getText().toString().equals("")){
                    if (sqlite.validateUser(username.getText().toString().trim(), password.getText().toString().trim()) == true) {
                      Intent intent = new Intent(Login.this, MainActivity.class);
                       startActivity(intent);

                    } else {
                        Toast.makeText(getBaseContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        signup1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent i = new Intent(Login.this, signupactivity.class);
                startActivity(i);


                ;
            }
        });

//        show.setOnTouchListener(new View.OnTouchListener() {
  //          @Override
    //        public boolean onTouch(View view, MotionEvent motionEvent) {
      //          switch (motionEvent.getAction()) {
        //            case MotionEvent.ACTION_DOWN:
          //              editText2.setInputType(InputType.TYPE_CLASS_TEXT);
            //            break;
              //      case MotionEvent.ACTION_UP:
                //        editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                  //      break;
              //  }
        //    return true;
        //    }

      //  });

    }


    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}



