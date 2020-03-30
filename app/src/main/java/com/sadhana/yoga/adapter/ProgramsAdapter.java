package com.sadhana.yoga.adapter;

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

import com.sadhana.yoga.R;
import com.sadhana.yoga.interfaces.OnItemListener;
import com.sadhana.yoga.model.Programs;

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
        final Programs program = programs.get(position);
        int imageId = context.getResources().getIdentifier(program.getProgramImage(), "drawable", context.getPackageName());
        holder.programImage.setImageResource(imageId);
        holder.programTitle.setText(program.getProgramTitle());
        holder.duration.setText(program.getDuration());

        Drawable isFavorite;
        if(readState(program.getProgramTitle())){
            isFavorite = ContextCompat.getDrawable(context,R.drawable.ic_favorite_purple);
        }else{
            isFavorite = ContextCompat.getDrawable(context, R.drawable.ic_favorite_blank);
        }
        holder.toggleButton.setBackgroundDrawable(isFavorite);

        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean isFavourite = readState(program.getProgramTitle());
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
                sharedPreferencesEdit.putBoolean(program.getProgramTitle(), isFavorite);
                sharedPreferencesEdit.apply();
            }

        });
    }

    private boolean readState(String programTitle) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Favorite",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(programTitle, false);
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }

    public class ProgramsViewHolder extends RecyclerView.ViewHolder {

        TextView programTitle, duration;
        ImageView programImage;
        ToggleButton toggleButton;

        public ProgramsViewHolder(View itemView) {
            super(itemView);
            programTitle = itemView.findViewById(R.id.program_title);
            programImage = itemView.findViewById(R.id.program_cover);
            duration = itemView.findViewById(R.id.duration_numbers);
            toggleButton = itemView.findViewById(R.id.favToggle);
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


