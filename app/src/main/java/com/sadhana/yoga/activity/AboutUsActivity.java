package com.sadhana.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sadhana.yoga.R;

public class AboutUsActivity extends AppCompatActivity {

    ImageView backArrow, aboutUsCover;
    TextView aboutUsTitle, aboutUsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        backArrow = findViewById(R.id.backArrow);
        aboutUsCover = findViewById(R.id.aboutUs_cover);
        aboutUsTitle = findViewById(R.id.aboutUs_title);
        aboutUsDetails = findViewById(R.id.aboutUs_details);
    }

    public void onBackClick(View view) {
        finish();
    }
}
