package com.example.runtrackerwizardv11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class RoundRecyclerAdapter extends RecyclerView.Adapter<RoundRecyclerAdapter.ViewHolder> {

    private int runId;
    public Program program;

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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_round, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        // populates
//        viewHolder.itemTitle.setText(titles[i]);
//        viewHolder.itemDetail.setText(details[i]);
//        viewHolder.itemImage.setImageResource(images[i]);
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
