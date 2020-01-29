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
import com.example.yoga.model.Asana;

import java.util.ArrayList;

public class AsanaAdapter extends RecyclerView.Adapter<AsanaAdapter.AsanaViewHolder> {

    ArrayList<Asana> asanas;
    OnItemListener onItemListener;
    Context context;
    LayoutInflater inflater;

    public AsanaAdapter(Context context, ArrayList<Asana> asanas, OnItemListener onItemListener) {
        this.context = context;
        this.asanas = asanas;
        this.onItemListener = onItemListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AsanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_asana, parent, false);
        return new AsanaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AsanaAdapter.AsanaViewHolder holder, int position) {
        Asana asana = asanas.get(position);

        holder.name.setText(asana.getName());
        holder.sanskritName.setText(asana.getSanskritName());

        int imageId = context.getResources().getIdentifier(asana.getImages(), "drawable", context.getPackageName());
        holder.image.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return asanas.size();
    }

    public class AsanaViewHolder extends RecyclerView.ViewHolder {

        TextView name, sanskritName;
        ImageView image;

        public AsanaViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.mk_name_asana);
            sanskritName = (TextView) itemView.findViewById(R.id.sanskrit_name_asana);
            image = (ImageView) itemView.findViewById(R.id.asana_image);

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
