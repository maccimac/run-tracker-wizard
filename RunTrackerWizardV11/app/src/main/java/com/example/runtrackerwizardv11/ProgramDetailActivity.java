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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgramDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        recyclerView = binding.recyclerRounds;
        layoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RoundRecyclerAdapter( 2);
        recyclerView.setAdapter(adapter);

        LocationHelper lh = new LocationHelper(this, findViewById(R.id.txtLocation), findViewById(R.id.txtDistance));

        binding.btnStartRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRun();
            }
        });


    }


    public void goToRun(){
        Intent i = new Intent(this, RunActivity.class);
        startActivity(i);
    }

//    public void goToDistanceTracker(View view) {
//        startActivity( new Intent(this.getApplicationContext(), DistanceTrackingActivity.class)
//        );
//    }
}