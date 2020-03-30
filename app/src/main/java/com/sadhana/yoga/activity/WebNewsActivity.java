package com.sadhana.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sadhana.yoga.R;

public class WebNewsActivity extends AppCompatActivity {

    public static final String URL_EXTRA = "URL_EXTRA";

    WebView webView;
    TextView error;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_news);

        webView = (WebView) findViewById(R.id.webViewID);
        error = (TextView) findViewById(R.id.error);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());

        String url = getIntent().getStringExtra(URL_EXTRA);

        if (url != null){
            webView.loadUrl(url);
        } else {
            error.setVisibility(View.VISIBLE);
            webView.setVisibility(View.INVISIBLE);
        }
    }
}
