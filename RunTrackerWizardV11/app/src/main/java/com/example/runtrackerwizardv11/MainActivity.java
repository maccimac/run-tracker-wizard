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
    }

    public void prog1(View view){
     goToProgramDetail(1);
    }
    public void prog2(View view){
        goToProgramDetail(2);
    }
    public void prog3(View view){
        goToProgramDetail(3);
    }
    public void prog4(View view){
        goToProgramDetail(4);
    }


    public void goToProgramDetail(int _id) {
        Intent i = new Intent(this, ProgramDetailActivity.class);
        i.putExtra("programId", _id);
        startActivity(i);
    }

}