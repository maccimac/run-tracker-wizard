package com.example.runtrackerwizardv11;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runtrackerwizardv11.databinding.RecyclerItemRoundBinding;

import com.google.android.material.snackbar.Snackbar;


public class RoundRecyclerAdapter extends RecyclerView.Adapter<RoundRecyclerAdapter.ViewHolder> {

    private int runId;
    public Program program;
    public RecyclerItemRoundBinding binding;


    public RoundRecyclerAdapter(Program _program){
        program = _program;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.recycler_item_round, viewGroup, false);
        View view = binding.getRoot();
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Program.Round r = program.course[i];

        String textRound = "Round " + (i+1) + "/" + (program.course.length + 1);
        String textRun = r.runMeter + " meter Run";
        String textRest = r.restSec + " secs Rest";

        binding.textRound.setText(textRound);
        binding.textRun.setText(textRun);
        binding.textRest.setText(textRest);
        if(r.restSec < 1){
            binding.textRest.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
                return program.course.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, runId + " Click detected on item " + position,
                                    Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
}
