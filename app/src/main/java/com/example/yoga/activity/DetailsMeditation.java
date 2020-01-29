package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.yoga.R;
import com.example.yoga.model.Meditation;
import com.example.yoga.model.MeditationResponse;
import com.example.yoga.model.Pranayama;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsMeditation extends AppCompatActivity {

    ImageView coverMeditation, backArrow;
    ArrayList<Meditation> meditations = new ArrayList<>();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_meditation);

        coverMeditation = findViewById(R.id.meditation_cover_image);
        backArrow = findViewById(R.id.backArrow);
        gson = new Gson();
        String json = loadJSONFromAsset();

        MeditationResponse meditationResponse = gson.fromJson(json, MeditationResponse.class);
        meditations = meditationResponse.getMeditations();
    }

    private String loadJSONFromAsset() {

        String json;
        try {
            InputStream is = getAssets().open("meditation.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void onBackClick(View view) {
        finish();
    }
}
