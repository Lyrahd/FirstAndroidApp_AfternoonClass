package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class RegistrationScreen extends AppCompatActivity {
    EditText fname,surname,uname,email, password, password2;
    Button btnRegister,btnviewAll;
    DBAdapter DatabaseAdapter;

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        fname = (EditText) findViewById(R.id.txtFirstName);
        surname = (EditText) findViewById(R.id.txtSurname);
        uname = (EditText) findViewById(R.id.txtUsername);
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPass);
        password2 = (EditText) findViewById(R.id.txtPass2);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnviewAll = (Button) findViewById(R.id.btnViewAll);

        DatabaseAdapter = new DBAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();
        viewAll();

        assert btnRegister != null;
       btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fname.getText().toString().equals("")||surname.getText().toString().equals("")||uname.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")||password2.getText().toString().equals("")){
                    Toast.makeText(RegistrationScreen.this, "Fill out all Remaining Fields", Toast.LENGTH_LONG).show();
                } else if (!isValidName(fname.getText().toString())){
                    fname.setError("Numbers and Special Numbers are Not Allowed");
                } else if (!isValidName(surname.getText().toString())){
                    surname.setError("Numbers and Special Numbers are Not Allowed");
                } else if(!isValidEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                } else if (DatabaseAdapter.ifExists(email.getText().toString())){
                    email.setError("Email Already Exists");
                } else if (!isValidUser(uname.getText().toString())) {
                    uname.setError("Invalid Username");
                } else if (DatabaseAdapter.ifExists(uname.getText().toString())){
                    uname.setError("Username Already Exists");
                } else if(!isValidPassword(password.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this, "Invalid Password", Toast.LENGTH_LONG).show();
                } else if ((isValidPassword(password.getText().toString()))!=(isValidConPassword(password2.getText().toString()))){
                    Toast.makeText(RegistrationScreen.this, "Password do not match", Toast.LENGTH_LONG).show();
                }
                else if(password.getText().toString().equals(password2.getText().toString())){
                    DatabaseAdapter.insertEntry(email.getText().toString(),
                                                password.getText().toString(),
                                                fname.getText().toString(),
                                                surname.getText().toString(),
                                                uname.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(RegistrationScreen.this,LoginScreen.class );
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
    private boolean isValidConPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }

    private boolean isValidName(String name) {
        String pat = "[A-Za-z]{1,}$";
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean isValidUser(String user) {
        String pat = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(user);
        return matcher.matches();
    }

    public void viewAll() {

        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = DatabaseAdapter.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("First Name:"+ res.getString(1)+"\n");
                            buffer.append("Surname:"+ res.getString(2)+"\n");
                            buffer.append("Username:"+ res.getString(3)+"\n");
                            buffer.append("Email:"+ res.getString(4)+"\n");
                            buffer.append("Password:"+ res.getString(5)+"\n");

                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }

}