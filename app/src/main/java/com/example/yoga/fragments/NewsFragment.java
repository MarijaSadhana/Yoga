package com.example.yoga.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.adapter.NewsAdapter;
import com.example.yoga.interfaces.OnNewsClickListener;


import java.util.ArrayList;

public class NewsFragment extends Fragment implements OnNewsClickListener {

    public static final String TAG = "NEWSTAG";

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ArrayList news = new ArrayList<>();

    public NewsFragment() {}

    public static NewsFragment newInstance() {
        NewsFragment newsFragment = new NewsFragment();
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getActivity(), news, this);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(getActivity(), WebNews.class);
        intent.putExtra("News", (Parcelable) news.get(position));
        startActivity(intent);
    }
}
