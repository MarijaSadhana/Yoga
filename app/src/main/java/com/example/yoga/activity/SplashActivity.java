package com.example.yoga.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.yoga.R;

public class SplashActivity extends Activity {

    VideoView videoView;
    String email;
    String password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        videoView = findViewById(R.id.splash_video_view);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setVolume(0,1);
            }
        });
    }

    private void startNextActivity() {

        sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);

        email = sharedPreferences.getString("email", null);
        password = sharedPreferences.getString("password", null);

        if (sharedPreferences.getBoolean("isLogedIn", false)) {
            Intent j = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(j);
            finish();
        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}


