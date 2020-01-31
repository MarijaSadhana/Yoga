package com.example.yoga.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoga.R;
import com.example.yoga.activity.DetailsAsana;
import com.example.yoga.adapter.SectionAdapter;
import com.example.yoga.common.Constants;
import com.example.yoga.interfaces.OnAsanaClickListener;
import com.example.yoga.model.AsanaResponse;
import com.example.yoga.model.Asanas;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static kotlin.text.Typography.section;

public class AsanaFragment extends Fragment implements OnAsanaClickListener {

    public static final String TAG = AsanaFragment.class.getSimpleName();

    RecyclerView recyclerView;
    SectionAdapter sectionAdapter;
    ArrayList<Asanas> sectionList = new ArrayList<>();
    Gson gson;

    public static AsanaFragment newInstance(){
        AsanaFragment asanaFragment = new AsanaFragment();
        return asanaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asana, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_asana);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        gson = new Gson();
        String json = loadJSONFromAsset();
        AsanaResponse asanaResponse = gson.fromJson(json, AsanaResponse.class);
        sectionList = asanaResponse.getAsanas();

//        sectionAdapter = new SectionAdapter(getContext(), sectionList, this);
        recyclerView.setAdapter(sectionAdapter);
        loadBooks(Constants.SectionType.BEGINNERS);
        loadBooks(Constants.SectionType.INTERMEDIATE);
        loadBooks(Constants.SectionType.ADVANCED);

        return view;
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

    private void loadBooks(int section) {
        String json = "";

        switch (section){
            case Constants.SectionType.BEGINNERS:
                json = "asana.json";
                break;
            case Constants.SectionType.INTERMEDIATE:
                json = "asana.json";
                break;
            case Constants.SectionType.ADVANCED:
                json = "asana.json";
                break;
        }
    }

    @Override
    public void onAsanaClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsAsana.class);
        intent.putExtra("Asanas", (Parcelable) sectionList.get(position));
        startActivity(intent);

    }
}
