package com.example.yoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.interfaces.OnNewsClickListener;
import com.example.yoga.model.News;
import com.example.yoga.model.Result;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<Result> results;
    LayoutInflater inflater;
    Context context;
    OnNewsClickListener onNewsClickListener;

    public NewsAdapter(Context context, ArrayList<Result> results, OnNewsClickListener onNewsClickListener) {
        this.context = context;
        this.results = results;
        this.onNewsClickListener = onNewsClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        Result result = results.get(position);
        holder.webTitle.setText(result.getWebTitle());
        holder.pillarName.setText(result.getPillarName());
        holder.webPublicationDate.setText(result.getWebPublicationDate());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView webTitle, pillarName, webPublicationDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            webTitle = itemView.findViewById(R.id.web_title);
            pillarName = itemView.findViewById(R.id.pillar_name);
            webPublicationDate = itemView.findViewById(R.id.web_publication_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNewsClickListener != null) {
                        onNewsClickListener.onNewsClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
