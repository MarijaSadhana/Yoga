package com.example.yoga.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yoga.R;
import com.example.yoga.interfaces.OnAsanaClickListener;
import com.example.yoga.model.AsanaResponse;
import com.example.yoga.model.Asanas;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailsAsana extends AppCompatActivity {

    ImageView backArrow, coverImage;
    TextView asanaMkTitle, asanaSanskritTitle, asanaDetails, izvedba;
    Asanas asana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_asana);

        coverImage = findViewById(R.id.asana_cover_image);
        backArrow = findViewById(R.id.backArrow);
        izvedba = findViewById(R.id.izvedba);
        asanaMkTitle = findViewById(R.id.asana_title);
        asanaSanskritTitle = findViewById(R.id.asana_sanskrit_title);
        asanaDetails = findViewById(R.id.asana_details_text);

        asana = getIntent().getParcelableExtra("Asanas");
        if (asana != null) {
            asanaMkTitle.setText(asana.getAsanaName());
            asanaSanskritTitle.setText(asana.getSanskritName());
            asanaDetails.setText(asana.getAsanaDetails());
        }

        String imgName = asana.getAsanaImages();
        int resID = getResources().getIdentifier(imgName , "drawable", getPackageName());
        coverImage.setImageResource(resID);
    }
    
    public void onBackClick(View view) {
        finish();
    }

}
