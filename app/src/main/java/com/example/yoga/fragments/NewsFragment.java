package com.example.yoga.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.yoga.R;
import com.example.yoga.adapter.NewsAdapter;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.News;
import com.example.yoga.model.NewsResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment implements OnItemListener {

    public static final String TAG = "NEWSTAG";

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ArrayList<News> newsList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    TextView webTitle, webUrl, pillarName, webPublicationDate;
    Gson gson;

    public NewsFragment() {}

    public static NewsFragment newInstance() {
        NewsFragment newsFragment = new NewsFragment();
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_news);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        webTitle = view.findViewById(R.id.web_title);
        webUrl = view.findViewById(R.id.web_url);
        pillarName = view.findViewById(R.id.pillar_name);
        webPublicationDate = view.findViewById(R.id.web_publication_date);

        newsAdapter = new NewsAdapter(newsList, this, getActivity());
        recyclerView.setAdapter(newsAdapter);
        gson = new Gson();

        loadNews();
    }

    private void loadNews() {
        String url = "https://content.guardianapis.com/search?q=yoga%20AND&20meditation&api-key=d1191012-4836-4034-ab96-0ba3efed67af";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String jsonString = response.body().string();
                    Log.d(TAG, jsonString);

                   NewsResponse newsResponse = gson.fromJson(jsonString, NewsResponse.class);
                   Log.d(TAG, String.valueOf(newsResponse.getResponse()));
//                     newsList.add(newsResponse);

                     getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            newsAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), WebNews.class);
        intent.putExtra(WebNews.NEWS, position);
        startActivity(intent);
    }

    @Override
    public void onSeeAllClick(int sectionType, int asnaCategory) {

    }

}
