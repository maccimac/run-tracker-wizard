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

    private  String[] titles = {
            "Chapter One", "Chapter Two", "Chapter Three", "Chapter Four",
            "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight"
    };

    private String[] details = {
            "Lorem ipsum one",
            "Lorem ipsum two",
            "Lorem ipsum three",
            "Lorem ipsum four",
            "Lorem ipsum five",
            "Lorem ipsum six",
            "Lorem ipsum seven",
            "Lorem ipsum eight"
    };


    public RoundRecyclerAdapter(int _id){
        runId = _id;
        program = new Program(_id);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.recycler_item_round, viewGroup, false);
        View view = binding.getRoot();
        return new ViewHolder(view);

//        binding = RecyclerItemRoundBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(v);

//        View v = LayoutInflat/*er.from(viewGroup.getContext()).inflate(R.layout.recycler_item_round, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;*/
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Program.Round r = program.course[i];

        String textRound = "Round " + (i+1) + "/" + (program.course.length + 1);
        String textRun = r.runMeter + " meter Run";
        String textRest = (r.restSec/60) + " min Rest";

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
//        return titles.length;
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
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView itemImage;
//        TextView itemTitle;
//        TextView itemDetail;
//
//        public ViewHolder(@NonNull View itemView){
//            super(itemView);
////            itemImage = itemView.findViewById(R.id.item_image);
////            itemTitle = itemView.findViewById(R.id.item_title);
////            itemDetail = itemView.findViewById(R.id.item_detail);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Snackbar.make(v, "Click detected on item " + position,
//                                    Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }
//    }
}
