package com.example.yoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.Meditation;
import com.example.yoga.model.Programs;

import java.util.ArrayList;
import java.util.List;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder> {

    List<Programs> programs;
    LayoutInflater inflater;
    Context context;
    OnItemListener onItemListener;

    public ProgramsAdapter(Context context, ArrayList<Programs> programs, OnItemListener onItemListener) {
        this.context = context;
        this.programs = programs;
        this.onItemListener = onItemListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProgramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_program, parent, false);
        return new ProgramsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgramsViewHolder holder, final int position) {
        Programs program = programs.get(position);

        int imageId = context.getResources().getIdentifier(program.getProgramImage(), "drawable", context.getPackageName());
        holder.programImage.setImageResource(imageId);
        holder.programTitle.setText(program.getProgramTitle());
        holder.duration.setText(program.getDuration());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class ProgramsViewHolder extends RecyclerView.ViewHolder {

        TextView programTitle, duration;
        ImageView programImage;

        public ProgramsViewHolder(View itemView) {
            super(itemView);
            programTitle = itemView.findViewById(R.id.program_title);
            programImage = itemView.findViewById(R.id.program_cover);
            duration = itemView.findViewById(R.id.duration_numbers);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemListener != null) {
                        onItemListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}

