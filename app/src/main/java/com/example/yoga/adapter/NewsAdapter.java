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
import com.example.yoga.model.Result;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

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
            final Result result = results.get(position);
            newsViewHolder.webTitle.setText(result.getWebTitle());
            newsViewHolder.pillarName.setText(result.getPillarName());
            newsViewHolder.webPublicationDate.setText(result.getWebPublicationDate());
            newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNewsClickListener != null) {
                        onNewsClickListener.onNewsClick(result.getWebUrl());
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
        return results == null ? 0 : results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return results.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView webTitle, pillarName, webPublicationDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            webTitle = itemView.findViewById(R.id.web_title);
            pillarName = itemView.findViewById(R.id.pillar_name);
            webPublicationDate = itemView.findViewById(R.id.web_publication_date);
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
