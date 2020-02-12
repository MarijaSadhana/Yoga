package com.example.yoga.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoga.R;
import com.example.yoga.activity.DetailsPranayama;
import com.example.yoga.adapter.PranayamaAdapter;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.Pranayama;
import com.example.yoga.model.PranayamaResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class PranayamaFragment extends Fragment implements OnItemListener {

    RecyclerView recyclerView;
    PranayamaAdapter adapter;
    ArrayList<Pranayama> pranayamas = new ArrayList<>();
    Gson gson;

    public PranayamaFragment() {}

    public static PranayamaFragment newInstance() {
        PranayamaFragment pranayamaFragment = new PranayamaFragment();
        return pranayamaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pranayama, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_pranayama);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        gson = new Gson();
        String json = loadJSONFromAsset();

        PranayamaResponse pranayamaResponse = gson.fromJson(json, PranayamaResponse.class);
        pranayamas = pranayamaResponse.getPranayamas();

        adapter = new PranayamaAdapter(getActivity(), pranayamas, (OnItemListener) this);
        recyclerView.setAdapter(adapter);
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("pranayama.json");
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
        Intent intent = new Intent(getActivity(), DetailsPranayama.class);
        intent.putExtra("Pranayama", pranayamas.get(position));
        startActivity(intent);
    }
}
