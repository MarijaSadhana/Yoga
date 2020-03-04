package com.example.yoga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.interfaces.OnNewsClickListener;
import com.example.yoga.model.Articles;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    ArrayList<Articles> articles;
    LayoutInflater inflater;
    Context context;
    OnNewsClickListener onNewsClickListener;

    public NewsAdapter(Context context, ArrayList<Articles> articles, OnNewsClickListener onNewsClickListener) {
        this.context = context;
        this.articles = articles;
        this.onNewsClickListener = onNewsClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new NewsViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            final Articles article = articles.get(position);
            newsViewHolder.webTitle.setText(article.getTitle());
            newsViewHolder.webPublicationDate.setText(article.getPublishedAt());
            newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNewsClickListener != null) {
                        onNewsClickListener.onNewsClick(article.getUrl());
                    }
                }
            });
        } else if (holder instanceof LoadingHolder) {
            LoadingHolder loadingHolder = (LoadingHolder) holder;
            loadingHolder.progressBarLoad.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return articles.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView webTitle, webPublicationDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            webTitle = itemView.findViewById(R.id.web_title);
            webPublicationDate = itemView.findViewById(R.id.published_at);
        }
    }

    public class LoadingHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBarLoad;

        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
            progressBarLoad = itemView.findViewById(R.id.progressBarLoad);
        }
    }
}
