package com.example.shamsad.volly_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.tSignUpPrompt);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        }

        );
    }
}
