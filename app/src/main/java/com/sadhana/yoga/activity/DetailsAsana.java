package com.sadhana.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sadhana.yoga.R;
import com.sadhana.yoga.model.Asanas;

public class DetailsAsana extends AppCompatActivity {

    ImageView backArrow, coverImage;
    TextView asanaMkTitle, asanaSanskritTitle, asanaDetails, izvedba;
    Asanas asana;
    Menu menu;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_asana);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

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

        String imgName = null;
        if (asana != null) {
            imgName = asana.getAsanaImages();
        }
        int resID = getResources().getIdentifier(imgName , "drawable", getPackageName());
        coverImage.setImageResource(resID);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String videoId = asana.getAsanaVideo();
//                Intent asanaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoId));
//                asanaIntent.putExtra("force_fullscreen",true);
//                startActivity(asanaIntent);
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        this.menu = menu;
//        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
////        hideOption(R.id.action_info);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//////         Handle action bar item clicks here. The action bar will
//////         automatically handle clicks on the Home/Up button, so long
//////         as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_favorite) {
////            return true;
////        } else if (id == R.id.action_info) {
////            return true;
////        }
////
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void hideOption(int id) {
//        MenuItem item = menu.findItem(id);
//        item.setVisible(false);
//    }
//
//    private void showOption(int id) {
//        MenuItem item = menu.findItem(id);
//        item.setVisible(true);
//    }

    public void onBackClick(View view) {
        finish();
    }
}
