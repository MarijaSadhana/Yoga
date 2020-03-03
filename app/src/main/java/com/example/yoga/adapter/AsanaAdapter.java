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
import com.example.yoga.interfaces.OnAsanaClickListener;
import com.example.yoga.model.Asanas;

import java.util.ArrayList;
import java.util.List;

public class AsanaAdapter extends RecyclerView.Adapter<AsanaAdapter.AsanaViewHolder> {

    List<Asanas> asanas;
    LayoutInflater inflater;
    OnAsanaClickListener onAsanaClickListener;
    Context context;

    public AsanaAdapter(ArrayList<Asanas> asanas, OnAsanaClickListener onAsanaClickListener, Context context) {
        this.asanas = asanas;
        this.onAsanaClickListener = onAsanaClickListener;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AsanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_asana, parent, false);
        return new AsanaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsanaViewHolder holder, int position) {
        Asanas asana = asanas.get(position);
        int imageId = context.getResources().getIdentifier(asana.getAsanaImages(), "drawable", context.getPackageName());
        holder.imageView.setImageResource(imageId);
        holder.mkName.setText(asana.getAsanaName());
        holder.sanskName.setText(asana.getSanskritName());
        holder.category.setText(asana.getAsanaCategory());
    }

    @Override
    public int getItemCount() {
        return asanas.size();
    }

    public class AsanaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView mkName, sanskName, category;

        public AsanaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.asana_image);
            mkName = itemView.findViewById(R.id.mk_name_asana);
            sanskName = itemView.findViewById(R.id.sanskrit_name_asana);
            category = itemView.findViewById(R.id.asana_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAsanaClickListener != null){
                        onAsanaClickListener.onAsanaClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
