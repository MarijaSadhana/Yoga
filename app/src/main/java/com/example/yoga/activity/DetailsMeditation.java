package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yoga.R;
import com.example.yoga.model.Meditation;


public class DetailsMeditation extends AppCompatActivity {

    ImageView meditationCover, backArrow, videoPlay;
    TextView meditationTitle, meditationDescription;
    Meditation meditation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meditation);

        meditationCover = findViewById(R.id.meditation_cover_image);
        videoPlay = findViewById(R.id.video_play_image);
        backArrow = findViewById(R.id.backArrow);
        meditationTitle = findViewById(R.id.meditation_title);
        meditationDescription = findViewById(R.id.meditation_description);

        meditation = getIntent().getParcelableExtra("Meditation");
        if (meditation != null) {
            meditationTitle.setText(meditation.getMeditationTitle());
            meditationDescription.setText(meditation.getMeditationDescription());
        }

        String imgName = null;
        if (meditation != null) {
            imgName = meditation.getMeditationImage();
        }
        int resID = getResources().getIdentifier(imgName , "drawable", getPackageName());
        meditationCover.setImageResource(resID);

        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoId = meditation.getMeditationVideo();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
                intent.putExtra("force_fullscreen",true);
                startActivity(intent);
            }
        });
    }

    public void onBackClick(View view) {
        finish();
    }
}
