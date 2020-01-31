package com.example.yoga.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.model.Asanas;

import java.util.ArrayList;

public class AsanaAdapter extends RecyclerView.Adapter<AsanaAdapter.AsanaViewHolder> {

    ArrayList<Asanas> asanas;
    LayoutInflater inflater;
    Context context;

    public AsanaAdapter(Context context, ArrayList<Asanas> asanas){
        this.context = context;
        this.asanas = asanas;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AsanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_asana_section, parent, false);
        return new AsanaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsanaViewHolder holder, int position) {

        Asanas asana = asanas.get(position);
        holder.mkName.setText(asana.getAsanaName());
        holder.sansName.setText(asana.getSanskritName());
        holder.asanaImage.setImageDrawable(Drawable.createFromPath(asana.getAsanaImages()));
    }

    @Override
    public int getItemCount() {
        return asanas.size();
    }

    public class AsanaViewHolder extends RecyclerView.ViewHolder {

        ImageView asanaImage;
        TextView mkName, sansName;

        public AsanaViewHolder(@NonNull View itemView) {
            super(itemView);
            asanaImage = itemView.findViewById(R.id.asana_image);
            mkName = itemView.findViewById(R.id.mk_name_asana);
            sansName = itemView.findViewById(R.id.sanskrit_name_asana);
        }
    }
}
