package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by VielPC on 7/22/2016.
 */
public class Login extends Activity {

    /**    EditText editText_username;
     * EditText editText_password;
     * Button button_submit;
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    /**        editText_username = (EditText) findViewById(R.id.editText_user);
     *     editText_password = (EditText) findViewById(R.id.editText_password);
     *    button_submit = (Button) findViewById(R.id.button_submit);
     */
    public void loginb(View view) {
        final EditText editText_username = (EditText) findViewById(R.id.editText_user);
        final EditText  editText_password = (EditText) findViewById(R.id.editText_password);
        final Button button_submit = (Button) findViewById(R.id.button_submit);

        if (!validateEmail(editText_username.getText().toString())){
            editText_username.setError("Invalid Email");
            editText_username.requestFocus();
        }
        else
        if (!validatePwd(editText_password.getText().toString())) {
            editText_password.setError("Invalid Password");
            editText_password.requestFocus();
        }
        else {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private Boolean validateEmail(String emailAdd){
        if(emailAdd==null||!Patterns.EMAIL_ADDRESS.matcher(emailAdd).matches()){
            return false;}
        else{
            return true;}
    }


    private Boolean validatePwd(String password){
        if(password!=null&&password.length()>=8)
        {
            return true;
        }
        else {
            return false;}
    }
}