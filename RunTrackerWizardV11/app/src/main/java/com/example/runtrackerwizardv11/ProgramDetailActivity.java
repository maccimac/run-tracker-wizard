package com.example.runtrackerwizardv11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.runtrackerwizardv11.databinding.ActivityProgramDetailBinding;

public class ProgramDetailActivity extends AppCompatActivity {
    private ActivityProgramDetailBinding binding;
    RecyclerView recyclerView;
    RoundRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    int progId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgramDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();

        progId = extras.getInt("programId");

        recyclerView = binding.recyclerRounds;
        layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Program prog = new Program(progId);
        adapter = new RoundRecyclerAdapter(prog);
        recyclerView.setAdapter(adapter);

        binding.textView3.setText(prog.name);
        binding.textView.setText(prog.level);
        binding.btnStartRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRun();
            }
        });
    }

    public void goToRun(){
        Intent i = new Intent(this, RunActivity.class);
        i.putExtra("programId", progId);
        startActivity(i);
    }
}