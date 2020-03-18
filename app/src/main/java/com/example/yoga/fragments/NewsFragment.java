package com.example.yoga.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoga.R;
import com.example.yoga.activity.WebNewsActivity;
import com.example.yoga.adapter.NewsAdapter;
import com.example.yoga.interfaces.OnNewsClickListener;
import com.example.yoga.model.Articles;
import com.example.yoga.model.NewsResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment implements OnNewsClickListener {

    public static final String TAG = "NEWSTAG";

    RecyclerView recyclerView;
    ImageView imageView;
    TextView date;
    NewsAdapter newsAdapter;
    ArrayList<Articles> news = new ArrayList<>();
    Gson gson;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
//    LinearLayoutManager linearLayoutManager;
//    Boolean loading = true;
//    int page = 1;
//    int visibleItemCount, totalItemCount, pastVisibleItems;

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
        imageView = view.findViewById(R.id.news_logo);
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
        final String url = "https://newsapi.org/v2/everything?q=yoga&apiKey=2dccbc8dff684538bd586569a88b78b6";
        Request request = new Request.Builder().url(url).get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonString = Objects.requireNonNull(response.body()).string();
                    NewsResponse newsResponse = gson.fromJson(jsonString, NewsResponse.class);
                    for (int i = 0; i < newsResponse.getArticles().size(); i++) {
                        news.add(i, newsResponse.getArticles().get(i));
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

            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0)
//                {
//                    visibleItemCount = linearLayoutManager.getChildCount();   // =0  llmanager = null ??????
//                    totalItemCount = linearLayoutManager.getItemCount();
//                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                    if (!loading) {
//                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
//                            loading = true;
                            //page = page+1;
////                            loadMore();
//                        }
//                    }
//                }
//            }

//            private void loadMore() {
//                Request request = new Request.Builder().url(url).get().build();

//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
//
//                    }

//                    @Override
//                    public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
        });
    }

    private void myUpdateOperation() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Proverka dali korisnikot napravil refresh:
        if (item.getItemId() == R.id.menu_refresh) {
            Log.i(TAG, "Refresh menu item selected");

            swipeRefreshLayout.setRefreshing(true);

            // Start na refresh background task
            myUpdateOperation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewsClick(String url) {
        Intent intent = new Intent(getActivity(), WebNewsActivity.class);
        intent.putExtra(WebNewsActivity.URL_EXTRA, url);
        startActivity(intent);
    }
}
