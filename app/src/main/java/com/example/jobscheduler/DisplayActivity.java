package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    TextView displayText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        displayText = findViewById(R.id.display_text);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

        String data = bundle.getString("nameChosen");
        int age = bundle.getInt("AgeGiven", 0);

        displayText.setText(data + "is "+ age +" old");


        }

    }
}
