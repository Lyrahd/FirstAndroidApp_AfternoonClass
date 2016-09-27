package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by androidstudio on 9/17/16.
 */
public class SignUp extends AppCompatActivity {

    DatabaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        loginDataBaseAdapter=new DatabaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        final EditText fname = (EditText) findViewById(R.id.fname);
        final EditText Lname = (EditText) findViewById(R.id.Lname);
        final EditText uname = (EditText) findViewById(R.id.Username);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmpass = (EditText) findViewById(R.id.confpassword);
        final Button validate = (Button) findViewById(R.id.okbtn);

        assert validate != null;
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = fname.getText().toString();
                String ln = Lname.getText().toString();
                String un = uname.getText().toString();
                String em = email.getText().toString();
                String p = password.getText().toString();
                String cp = confirmpass.getText().toString();
                if(em.equals("")||p.equals("")||cp.equals(""))
                    {Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_SHORT).show();
                    return;}

                if (!validateEmail(em))
                    {email.setError("Invalid Email!");
                     email.requestFocus();}

                if(!validatePassword(password.getText().toString()))
                    {password.setError("Invalid Password!");
                     password.requestFocus();}

                if (!password.getText().toString().equals(confirmpass.getText().toString()))
                    {Toast.makeText(SignUp.this, "Password does not match the confirm password.", Toast.LENGTH_SHORT).show();}

                if (validateEmail(em) && p == cp)
                    {Toast.makeText(SignUp.this, "Password Match.", Toast.LENGTH_SHORT).show();
                     loginDataBaseAdapter.insertEntry(em, p,fn,ln,un);
                     Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(SignUp.this,MainActivity.class );
                     startActivity(intent);}
            }
        });







    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
    private boolean validateEmail(String email) {
        String emailRegex;
        Pattern pattern;

        emailRegex = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password){
        if(password!=null && password.length()>7){
            return true;
        }
        else {
            return false;
        }
    }
}