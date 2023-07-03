package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.runtrackerwizardv11.databinding.ActivityProgramDetailBinding;

public class ProgramDetailActivity extends AppCompatActivity {
    private ActivityProgramDetailBinding binding;
    RecyclerView recyclerView;
    RoundRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgramDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        setContentView(R.layout.activity_program_detail);

        // get intent

        //
        recyclerView = binding.recyclerRounds;
        layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RoundRecyclerAdapter(2);
        recyclerView.setAdapter(adapter);
    }
}