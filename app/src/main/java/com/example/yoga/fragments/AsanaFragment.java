package com.example.yoga.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.yoga.R;
import com.example.yoga.activity.DetailsAsana;
import com.example.yoga.activity.DetailsMeditation;
import com.example.yoga.activity.SeeAllAsanas;
import com.example.yoga.adapter.AsanaAdapter;
import com.example.yoga.adapter.MeditationAdapter;
import com.example.yoga.common.Constants;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.Asana;
import com.example.yoga.model.AsanaResponse;
import com.example.yoga.model.Meditation;
import com.example.yoga.model.MeditationResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AsanaFragment extends Fragment implements OnItemListener {

    public static final String TAG = AsanaFragment.class.getSimpleName();
    public static final String TYPE = "ASANATAG";
    public static final int ASANA = 0;

    RecyclerView recyclerView;
    AsanaAdapter asanaAdapter;
    ArrayList<Asana> asanas = new ArrayList<>();
    Gson gson;

    public AsanaFragment() {}

    public static AsanaFragment newInstance() {
        AsanaFragment asanaFragment = new AsanaFragment();
        return asanaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asana, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_asana);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        gson = new Gson();
        String json = loadJSONFromAsset();

        AsanaResponse asanaResponse = gson.fromJson(json, AsanaResponse.class);
        asanas = asanaResponse.getAsanas();

        asanaAdapter = new AsanaAdapter(getActivity(), asanas, this);
        recyclerView.setAdapter(asanaAdapter);
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("asana.json");
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
        Intent intent = new Intent(getActivity(), DetailsAsana.class);
        intent.putExtra("Asana", (Parcelable) asanas.get(position));
        startActivity(intent);
    }

    @Override
    public void onSeeAllClick(int sectionType, int asnaCategory) {

    }

}
