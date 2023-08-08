package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
    }

    public void goToStart(){
        startActivity(new Intent(
                DoneActivity.this, MainActivity.class
        ));
    }
}