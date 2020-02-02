package com.example.yoga.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoga.R;
import com.example.yoga.adapter.NewsAdapter;
import com.example.yoga.interfaces.OnNewsClickListener;
import com.example.yoga.interfaces.OnRefreshListener;
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

public class NewsFragment extends Fragment implements OnNewsClickListener, OnRefreshListener {

    public static final String TAG = "NEWSTAG";

    RecyclerView recyclerView;
    TextView category, date;
    NewsAdapter newsAdapter;
    ArrayList news = new ArrayList<>();
    Gson gson;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

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
        gson = new Gson();
        category = view.findViewById(R.id.textCategory);
        date = view.findViewById(R.id.textDatePublished);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recycler_view_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(getActivity(), news, this);
        recyclerView.setAdapter(newsAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh called from SwipeRefreshLayout");
                myUpdateOperation();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        OkHttpClient client = new OkHttpClient();
        String url = "https://content.guardianapis.com/search?page-size=10&q=yoga%20AND&20meditation&api-key=d1191012-4836-4034-ab96-0ba3efed67af";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonString = response.body().string();
                    NewsResponse newsResponse = gson.fromJson(jsonString, NewsResponse.class);
                    for (int i = 0; i < newsResponse.getResponse().getPageSize(); i++) {
                        news.add(i, newsResponse.getResponse().getResults().get(i));
                    }

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                newsAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }

                }
            }
        });
    }

    private void myUpdateOperation() {

    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(getActivity(), WebNews.class);
        intent.putExtra("News", (Parcelable) news.get(position));
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if user triggered a refresh:
        if (item.getItemId() == R.id.menu_refresh) {
            Log.i(TAG, "Refresh menu item selected");

            // Signal SwipeRefreshLayout to start the progress indicator
            swipeRefreshLayout.setRefreshing(true);

            // Start the refresh background task.
            // This method calls setRefreshing(false) when it's finished.
            myUpdateOperation();
            return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {

    }
}
