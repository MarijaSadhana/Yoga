package com.example.yoga.fragments;


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

import com.example.yoga.R;
import com.example.yoga.activity.DetailProgram;
import com.example.yoga.adapter.ProgramsAdapter;
import com.example.yoga.interfaces.OnItemListener;
import com.example.yoga.model.Programs;
import com.example.yoga.model.ProgramsResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProgramsFragment extends Fragment implements OnItemListener {

    public static final String TAG = "PROGTAG";

    RecyclerView recyclerView;
    ProgramsAdapter programsAdapter;
    ArrayList<Programs> programs = new ArrayList<>();
    Gson gson;

    public ProgramsFragment(){}

    public static ProgramsFragment newInstance() {
        ProgramsFragment programsFragment = new ProgramsFragment();
        return programsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_programs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gson = new Gson();
        String json = loadJSONFromAsset();

        ProgramsResponse programsResponse = gson.fromJson(json, ProgramsResponse.class);
        programs = programsResponse.getPrograms();

        programsAdapter = new ProgramsAdapter(getActivity(), programs, this);
        recyclerView.setAdapter(programsAdapter);
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("program.json");
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
        Intent intent = new Intent(getActivity(), DetailProgram.class);
        intent.putExtra("Program", (Parcelable) programs.get(position));
        startActivity(intent);

    }
}
