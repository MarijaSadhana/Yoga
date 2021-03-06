package com.sadhana.yoga.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sadhana.yoga.R;
import com.sadhana.yoga.activity.DetailsMeditation;
import com.sadhana.yoga.adapter.MeditationAdapter;
import com.sadhana.yoga.interfaces.OnItemListener;
import com.sadhana.yoga.model.Meditation;
import com.sadhana.yoga.model.MeditationResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MeditationFragment extends Fragment implements OnItemListener {

    RecyclerView recyclerView;
    TextView duration;
    MeditationAdapter meditationAdapter;
    ArrayList<Meditation> meditations = new ArrayList<>();
    Gson gson;

    public MeditationFragment() {}

    public static MeditationFragment newInstance() {
        MeditationFragment meditationFragment = new MeditationFragment();
        return meditationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meditation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        duration = view.findViewById(R.id.duration_text_medit);
        recyclerView = view.findViewById(R.id.recycler_view_meditation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gson = new Gson();
        String json = loadJSONFromAsset();

        MeditationResponse meditationResponse = gson.fromJson(json, MeditationResponse.class);
        meditations = meditationResponse.getMeditations();

        meditationAdapter = new MeditationAdapter(getActivity(), meditations, this);
        recyclerView.setAdapter(meditationAdapter);
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("meditation.json");
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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsMeditation.class);
        intent.putExtra("Meditation", (Parcelable) meditations.get(position));
        startActivity(intent);
        }

   }
