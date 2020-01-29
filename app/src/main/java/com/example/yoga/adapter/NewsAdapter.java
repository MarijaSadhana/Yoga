package com.example.yoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.fragments.NewsFragment;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.News;
import com.example.yoga.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> newsList;
    OnItemListener onItemListener;
    Context context;
    LayoutInflater inflater;

    public NewsAdapter(ArrayList<News> newsList, OnItemListener onItemListener, Context context) {
        this.newsList = newsList;
        this.onItemListener = onItemListener;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

        News news = newsList.get(position);
        holder.webTitle.setText(news.getWebTitle());
        holder.webUrl.setText(news.getWebUrl());
        holder.pillarName.setText(news.getPillarName());
        holder.publicationDate.setText(news.getPublicationDate());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView webTitle, webUrl, pillarName, publicationDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            webTitle = itemView.findViewById(R.id.web_title);
            webUrl = itemView.findViewById(R.id.web_url);
            pillarName = itemView.findViewById(R.id.pillar_name);
            publicationDate = itemView.findViewById(R.id.web_publication_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemListener != null){
                        onItemListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
