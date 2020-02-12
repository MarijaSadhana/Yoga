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

import java.util.ArrayList;
import java.util.List;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder> {

    List<Meditation> meditations;
    LayoutInflater inflater;
    Context context;
    OnItemListener onItemListener;

    public MeditationAdapter(Context context, ArrayList<Meditation> meditations, OnItemListener onItemListener) {
        this.context = context;
        this.meditations = meditations;
        this.onItemListener = onItemListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_meditation, parent, false);
        return new MeditationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {

        Meditation meditation = meditations.get(position);
        int imageId = context.getResources().getIdentifier(meditation.getMeditationImage(),"drawable", context.getPackageName());
        holder.image.setImageResource(imageId);
        holder.title.setText(meditation.getMeditationTitle());
        holder.duration.setText(meditation.getDuration());
    }

    @Override
    public int getItemCount() {
        return meditations.size();
    }

    public class MeditationViewHolder extends RecyclerView.ViewHolder{

        TextView title, duration;
        ImageView image;

        public MeditationViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_meditation);
            duration = itemView.findViewById(R.id.duration_numbers_medit);
            image = itemView.findViewById(R.id.audio_meditation);

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
