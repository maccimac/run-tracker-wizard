package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.runtrackerwizardv11.databinding.ActivityRunBinding;

public class RunActivity extends AppCompatActivity {
    ActivityRunBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRunBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_run);
    }
}