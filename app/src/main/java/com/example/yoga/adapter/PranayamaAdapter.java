package com.example.yoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.model.Pranayama;
import com.example.yoga.R;
import com.example.yoga.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

public class PranayamaAdapter extends RecyclerView.Adapter<PranayamaAdapter.PranayamaViewHolder> {

    List<Pranayama> pranayamas;
    LayoutInflater inflater;
    Context context;
    OnItemListener onItemListener;

    public PranayamaAdapter(Context context, ArrayList<Pranayama> pranayamas, OnItemListener onItemListener) {
        this.context = context;
        this.pranayamas = pranayamas;
        this.onItemListener = onItemListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PranayamaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pranayama, parent, false);
        return new PranayamaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PranayamaViewHolder holder, int position) {

        Pranayama pranayama = pranayamas.get(position);
        int imageId = context.getResources().getIdentifier(pranayama.getPranayamaImage(),"drawable", context.getPackageName());
        holder.image.setImageResource(imageId);
        holder.text.setText(pranayama.getPranayamaName());
        holder.sanskritText.setText(pranayama.getPranayamaSanskritName());
    }

    @Override
    public int getItemCount() {
        return pranayamas.size();
    }

    public class PranayamaViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        TextView sanskritText;

        public PranayamaViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_pranayama);
            text = itemView.findViewById(R.id.mk_name_pranayama);
            sanskritText = itemView.findViewById(R.id.sanskrit_name_pranayama);

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