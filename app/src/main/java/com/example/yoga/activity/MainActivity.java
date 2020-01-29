package com.example.yoga.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yoga.R;
import com.example.yoga.fragments.AsanaFragment;
import com.example.yoga.fragments.MeditationFragment;
import com.example.yoga.fragments.NewsFragment;
import com.example.yoga.fragments.PranayamaFragment;
import com.example.yoga.fragments.ProgramsFragment;
import com.example.yoga.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

//import static com.example.yoga.fragments.AsanaFragment.TYPE;


public class MainActivity extends AppCompatActivity {

//    ImageView asanaImage, pranayamaImage, meditationImage, programsImage, newsImage, settingsImage;
    DrawerLayout drawerLayout;
    LinearLayout layoutAsana, layoutPranayama, layoutMeditation, layoutPrograms, layoutNews, layoutSettings;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutAsana = (LinearLayout) findViewById(R.id.layout_asana);
        layoutAsana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        layoutPranayama = (LinearLayout) findViewById(R.id.layout_pranayama);
        layoutPranayama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        layoutMeditation = (LinearLayout) findViewById(R.id.layout_meditation);
        layoutMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        layoutPrograms = (LinearLayout) findViewById(R.id.layout_programs);
        layoutPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        layoutNews = (LinearLayout) findViewById(R.id.layout_news);
        layoutNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        layoutSettings = (LinearLayout) findViewById(R.id.layout_settings);
        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                onLayoutClick(v);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(toggle);
        }
        toggle.syncState();


        navigationView = findViewById(R.id.navigation_view);
        addFragment();
    }

    public void setFragment (Fragment fragment, String tag){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, fragment, tag);
//        ft.addToBackStack(tag);
        ft.commit();
    }

    private void addFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new AsanaFragment()).commit();
    }


    public void onLayoutClick(View view) {
        Fragment fragment = null;
        String tag = null;

        switch (view.getId()) {
            case R.id.layout_asana:
                fragment = AsanaFragment.newInstance();
//                tag = "ASANATAG";
                break;
            case R.id.layout_pranayama:
                fragment = PranayamaFragment.newInstance();
//                tag = "PRANATAG";
                break;
            case R.id.layout_meditation:
                fragment = MeditationFragment.newInstance();
//                tag = "MEDTAG";
                break;
            case R.id.layout_programs:
                fragment = ProgramsFragment.newInstance();
//                tag = "PROGTAG";
                break;
            case R.id.layout_news:
                fragment = NewsFragment.newInstance();
//                tag = "NEWSTAG";
                break;
            case R.id.layout_settings:
                fragment = SettingsFragment.newInstance();
//                tag = "SETTAG";
                break;
        }

        setFragment(fragment, tag);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
