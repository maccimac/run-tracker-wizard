package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.runtrackerwizardv11.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private  ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.cardRun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProgramDetail(2);
            }
        });
    }

    public void goToProgramDetail(int _id) {

        Intent i = new Intent(this, ProgramDetailActivity.class);
        i.putExtra("programId", _id);
        startActivity(i);
    }
}