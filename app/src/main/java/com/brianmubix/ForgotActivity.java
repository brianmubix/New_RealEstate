package com.brianmubix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotActivity extends AppCompatActivity {
    Button btn_reset;
    TextView textView_backToLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getSupportActionBar().hide();

        btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate

                startActivity(new Intent(getBaseContext(), MainActivity.class));

            }
        });

        textView_backToLogin = (TextView)findViewById(R.id.textView_backToLogin);
        textView_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Login.class));
            }
        });




    }
}
