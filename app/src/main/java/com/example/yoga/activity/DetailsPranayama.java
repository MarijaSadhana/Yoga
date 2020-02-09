package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.yoga.R;
import com.example.yoga.model.Pranayama;
import com.example.yoga.model.PranayamaResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsPranayama extends AppCompatActivity {

//    VideoView pranaVideo;
    ImageView coverImage, backArrow;
    TextView pranaTitle, pranaSanskritTitle, pranaDetails;
    Pranayama pranayama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pranayama);

//        pranaVideo = findViewById(R.id.pranayama_video);
        coverImage = findViewById(R.id.pranayama_cover_image);
        backArrow = findViewById(R.id.backArrow);
        pranaTitle = findViewById(R.id.pranayama_title);
        pranaSanskritTitle = findViewById(R.id.pranayama_sanskrit_title);
        pranaDetails = findViewById(R.id.pranayama_details_text);

        pranayama = getIntent().getParcelableExtra("Pranayama");
        if (pranayama != null) {
            pranaTitle.setText(pranayama.getPranayamaName());
            pranaSanskritTitle.setText(pranayama.getPranayamaSanskritName());
            pranaDetails.setText(pranayama.getPranayamaDetails());
        }

        String imgName = pranayama.getPranayamaImage();
        int resID = getResources().getIdentifier(imgName , "drawable", getPackageName());
        coverImage.setImageResource(resID);
    }

    public void onBackClick(View view) {
        finish();
    }

}

