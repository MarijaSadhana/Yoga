package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yoga.R;

public class SeeAllAsanas extends AppCompatActivity {

    public static final String SECTION_TYPE_EXTRA = "section_extra";
    public static final String ASANA_TYPE_EXTRA = "asana_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_asanas);
    }
}
