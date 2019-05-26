package com.brianmubix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView textView_register, textView_forgot ;
    EditText editText_username_email, editText_password;
    CheckBox checkbox_remember_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();



        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate
                editText_username_email = (EditText)findViewById(R.id.editText_username_email);
                editText_password = (EditText)findViewById(R.id.editText_password);

                String username_email = editText_username_email.getText().toString();
                String password = editText_password.getText().toString();


                if(username_email.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Username / Email Field Cannot be Empty",Toast.LENGTH_LONG).show();

                }else if(password.equals("")){
                    Toast.makeText(getBaseContext(),"Password Field Cannot be Empty",Toast.LENGTH_LONG).show();

                }
                else {

                    Login(username_email, password);
                }



            }
        });


        textView_register = (TextView)findViewById(R.id.textView_register);
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });

        textView_forgot = (TextView)findViewById(R.id.textView_forgot);
        textView_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ForgotActivity.class));
            }
        });
    }


    private void Login(String username_email2, String password2){


        //if logins are correct save the details in the app
        SharedPreferences logins = getApplicationContext().getSharedPreferences("UserLogins", 0); // 0 - for private mode
        SharedPreferences.Editor editor = logins.edit();

        editor.putString("username",username_email2);
        editor.commit();


        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();


    }
}
