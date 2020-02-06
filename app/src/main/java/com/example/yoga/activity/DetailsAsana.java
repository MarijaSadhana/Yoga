package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoga.R;
import com.example.yoga.model.AsanaResponse;
import com.example.yoga.model.Asanas;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsAsana extends AppCompatActivity {

    ImageView coverImage, backArrow;
    TextView asanaTitle, asanaSanskritTitle, asanaDetails;
    ArrayList<Asanas> asanas = new ArrayList<>();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_asana);

        coverImage = findViewById(R.id.asana_cover_image);
        backArrow = findViewById(R.id.backArrow);
        asanaTitle = findViewById(R.id.asana_title);
        asanaSanskritTitle = findViewById(R.id.asana_sanskrit_title);
        asanaDetails = findViewById(R.id.asana_details_text);

        gson = new Gson();
        String json = loadJSONFromAsset();

//        AsanaResponse asanaResponse = gson.fromJson(json, AsanaResponse.class);
//        asanas = asanaResponse.getAsanas();
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("asana.json");
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
