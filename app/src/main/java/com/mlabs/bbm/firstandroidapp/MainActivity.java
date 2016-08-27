package com.mlabs.bbm.firstandroidapp;

//import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.TextView;//text view declaration of pwd_show
import android.text.InputType;//class for show password

public class MainActivity extends AppCompatActivity {

    private EditText editUser;
    private EditText editPass;
    private TextView pwd_show; //Declaration for show password variable
    //Boolean onTouch = true; //For ShowPassword

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText) findViewById(R.id.editUser);
        editPass = (EditText) findViewById(R.id.editPass);
        pwd_show = (TextView)findViewById(R.id.pwd_show); //another declaration for pwd_show
        //below is the actual code of pwd_show based on STACK OVERFLOW
        pwd_show.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionevent){
                switch ( motionevent.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        editPass.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        editPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * for user name validation ONLY + password
                 */
                if (Pattern.compile("([a-zA-Z0-9]+_?)+").matcher(editUser.getText()).matches() && editPass.length()>=6)
                {
                    Intent i = new Intent(MainActivity.this,Display.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                }
                /**
                 * for email validation ONLY + password
                 */
                if (Pattern.compile("^\\w+.*\\w*@[a-zA-Z_]+?\\.[0-9a-zA-Z]{2,}$").matcher(editUser.getText()).matches() && editPass.length()>=6){
                    Intent i = new Intent(MainActivity.this,Display.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Welcome back Agent!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //Exit automatically code
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$#]).{6,20})";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}

