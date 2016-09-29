package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationScreen extends AppCompatActivity {
    EditText FName, LName, UName,Email, pass, pass2;
    Button btnRegister;
    DBAdapter DBAdapter;

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        FName = (EditText) findViewById(R.id.txtFName);
        LName = (EditText) findViewById(R.id.txtLName);
        UName = (EditText) findViewById(R.id.txtUName);
        Email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPass);
        pass2 = (EditText) findViewById(R.id.txtPass2);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        DBAdapter = new DBAdapter(this);
        DBAdapter = DBAdapter.open();

        assert btnRegister != null;
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String uname = UName.getText().toString();
                String Password = DBAdapter.getEmailforsignup(email);
                String Password1 = DBAdapter.getUsernameforsignup(uname);
                /**if ((isValidPassword(passu.getText().toString()))!=(isValidConPassword(conpassu.getText().toString())))  {
                 Toast.makeText(SignUp.this, "Password do not match", Toast.LENGTH_LONG).show(); } **/
                if(!isValidEmail(Email.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this,"Invalid Email",Toast.LENGTH_LONG).show();
                } else if(!isValidPassword(pass.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this, "Password Length needs to be at least 8 characters", Toast.LENGTH_LONG).show();
                }
                else if (!pass.getText().toString().equals(pass2.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
                else if(!isValidFname(FName.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this,"Invalid Firstname",Toast.LENGTH_LONG).show();
                }
                else if(!isValidLname(LName.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this,"Invalid Lastname",Toast.LENGTH_LONG).show();
                }
                else if(uname.equals(Password1)|email.equals(Password)){
                    Toast.makeText(RegistrationScreen.this,"Username or Email already exists",Toast.LENGTH_LONG).show();
                }
                else /**if(passu.getText().toString().equals(conpassu.getText().toString()))**/{
                    DBAdapter.insertEntry(FName.getText().toString(),LName.getText().toString(),UName.getText().toString(),Email.getText().toString(),pass.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(RegistrationScreen.this, MainActivity.class );
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }


    private boolean isValidFname(String fname) {

        //String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String FNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(FNAME_PATTERN);
        Matcher matcher = pattern.matcher(fname);
        return matcher.matches();
    }
    private boolean isValidLname(String lname) {

        //String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String LNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(LNAME_PATTERN);
        Matcher matcher = pattern.matcher(lname);
        return matcher.matches();
    }


}