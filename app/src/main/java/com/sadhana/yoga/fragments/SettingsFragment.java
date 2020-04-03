package com.sadhana.yoga.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sadhana.yoga.R;
import com.sadhana.yoga.activity.AboutUsActivity;
import com.sadhana.yoga.activity.SetAlarm;

public class SettingsFragment extends Fragment {

    LinearLayout alarmLayout, shareLayout, contactLayout, aboutUsLayout;
    ImageView fbImage, instaImage;

    public SettingsFragment() {}

    public static SettingsFragment newInstance() {
        SettingsFragment settingsFragment = new SettingsFragment();
        return settingsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alarmLayout = view.findViewById(R.id.layout_alarm);
//        favoriteLayout = view.findViewById(R.id.layout_favorite);
        shareLayout = view.findViewById(R.id.layout_share);
        contactLayout = view.findViewById(R.id.layout_contact);
        aboutUsLayout = view.findViewById(R.id.layout_aboutUs);
        fbImage = view.findViewById(R.id.fb_icon);
        instaImage = view.findViewById(R.id.insta_icon);

//        favoriteLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent favIntent = new Intent(getActivity(), FavoriteActivity.class);
//                startActivity(favIntent);
//            }
//        });

        alarmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarmIntent = new Intent(getActivity(), SetAlarm.class);
                startActivity(alarmIntent);
            }
        });

        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String body = "Јога";
                String subject = "https://play.google.com/store/apps/details?id=com.sadhana.yoga";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(shareIntent, "Сподели преку"));
            }
        });

        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto: jogasadana@yahoo.com"));
                startActivity(mailIntent);
            }
        });

        aboutUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        fbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbIntent;
                try {
                    fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1026023897479987"));
                    startActivity(fbIntent);
                } catch (ActivityNotFoundException e) {
                    fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/yogaskopje/"));
                    startActivity(fbIntent);
                }
            }
        });

        instaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inastaIntent;
                try {
                    inastaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/yoga_sadhana_/"));
                    startActivity(inastaIntent);
                } catch (ActivityNotFoundException e) {
                    inastaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/yoga_sadhana_/"));
                    startActivity(inastaIntent);
                }
            }
        });
    }
}
