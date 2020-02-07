package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.yoga.R;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.Meditation;
import com.example.yoga.model.MeditationResponse;
import com.example.yoga.model.Pranayama;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsMeditation extends AppCompatActivity  {

    VideoView videoView;
    ImageView backArrow;
    TextView meditationTitle, meditationDescription;
    Meditation meditation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meditation);

        videoView = findViewById(R.id.meditation_video);
        backArrow = findViewById(R.id.backArrow);
        meditationTitle = findViewById(R.id.meditation_title);
        meditationDescription = findViewById(R.id.meditation_description);

        meditation = getIntent().getParcelableExtra("Meditation");
        if (meditation != null) {
            meditationTitle.setText(meditation.getMeditationTitle());
            meditationDescription.setText(meditation.getMeditationDescription());
        }

        if (meditation.getMeditationVideo() != null) {
            String video = meditation.getMeditationVideo();
            int videoUri = getResources().getIdentifier(video, "assets", getPackageName());
            videoView.setVideoPath(String.valueOf(videoUri));
        }
    }

    public void onBackClick(View view) {
        finish();
    }


}
