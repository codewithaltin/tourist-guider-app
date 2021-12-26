package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Welcomer extends AppCompatActivity {
    private EditText welcomeUser;
    TextView firstName,lastName;
    Button btn;
    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomer);
        welcomeUser = findViewById(R.id.fullName);
        btn = findViewById(R.id.submitButton);
        goBack = findViewById(R.id.mainActivityButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = welcomeUser.getText().toString();

                Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
                intent.putExtra("keyname",fullName);
                startActivity(intent);

            }
        });

        goBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}