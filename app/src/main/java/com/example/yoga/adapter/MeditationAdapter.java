package com.example.yoga.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    public void onBindViewHolder(@NonNull final MeditationViewHolder holder, int position) {

        final Meditation meditation = meditations.get(position);
        int imageId = context.getResources().getIdentifier(meditation.getMeditationImage(),"drawable", context.getPackageName());
        holder.image.setImageResource(imageId);
        holder.title.setText(meditation.getMeditationTitle());
        holder.duration.setText(meditation.getDuration());

        Drawable isFavorite;
        if(readState(meditation.getMeditationTitle())){
            isFavorite = ContextCompat.getDrawable(context,R.drawable.ic_favorite_purple);
        }else{
            isFavorite = ContextCompat.getDrawable(context, R.drawable.ic_favorite_blank);
        }
        holder.toggleButton.setBackgroundDrawable(isFavorite);

        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isFavourite = readState(meditation.getMeditationTitle());
                if (!isFavourite) {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite_purple));
                    Toast.makeText(context,"Додадено во омилени",Toast.LENGTH_SHORT).show();
                    isFavourite = true;
                    saveState(isFavourite);
                }
                else {
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.ic_favorite_blank));
                    Toast.makeText(context,"Отстрането од омилени",Toast.LENGTH_SHORT).show();
                    isFavourite = false;
                    saveState(isFavourite);
                }
            }
            private void saveState(boolean isFavorite) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE);
                SharedPreferences.Editor sharedPreferencesEdit = sharedPreferences.edit();
                sharedPreferencesEdit.putBoolean(meditation.getMeditationTitle(), isFavorite);
                sharedPreferencesEdit.apply();
            }

        });
    }

    private boolean readState(String meditationTitle) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Favorite",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(meditationTitle, false);
    }

    @Override
    public int getItemCount() {
        return meditations.size();
    }

    public class MeditationViewHolder extends RecyclerView.ViewHolder{

        TextView title, duration;
        ImageView image;
        ToggleButton toggleButton;

        public MeditationViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_meditation);
            duration = itemView.findViewById(R.id.duration_numbers_medit);
            image = itemView.findViewById(R.id.audio_meditation);
            toggleButton = itemView.findViewById(R.id.favMedit);
            toggleButton.setChecked(false);
            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_blank));

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
