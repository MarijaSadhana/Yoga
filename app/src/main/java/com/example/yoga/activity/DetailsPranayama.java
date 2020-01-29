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
    ArrayList<Pranayama> pranayamas = new ArrayList<>();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_pranayama);

//        pranaVideo = findViewById(R.id.pranayama_video);
        coverImage = findViewById(R.id.prana_cover_image);
        backArrow = findViewById(R.id.backArrow);
        pranaTitle = findViewById(R.id.prana_title);
        pranaSanskritTitle = findViewById(R.id.prana_sanskrit_title);
        pranaDetails = findViewById(R.id.prana_details_text);

        gson = new Gson();
        String json = loadJSONFromAsset();

//        PranayamaResponse pranayamaResponse = gson.fromJson(json, PranayamaResponse.class);
//        pranayamas = pranayamaResponse.getPranayamas();

    }
//
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("pranayama.json");
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

