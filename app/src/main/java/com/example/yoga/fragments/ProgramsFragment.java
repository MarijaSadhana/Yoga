package com.example.yoga.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class ProgramsFragment extends Fragment implements OnItemListener {

    RecyclerView recyclerView;
    TextView durationText;
    ImageView favoriteImg;
    ProgramsAdapter programsAdapter;
    ArrayList<Programs> programs = new ArrayList<>();
    SharedPreferences sharedPreferences;
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
        durationText = view.findViewById(R.id.duration_text);
        favoriteImg = view.findViewById(R.id.favImg);
        recyclerView = view.findViewById(R.id.recycler_view_programs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gson = new Gson();
        String json = loadJSONFromAsset();

//        favoriteImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isFavourite = readState();
//
//                if (isFavourite) {
//                    favoriteImg.setBackgroundResource(R.drawable.ic_favorite_blank);
//                    isFavourite = false;
//                    saveState(isFavourite);
//
//                } else {
//                    favoriteImg.setBackgroundResource(R.drawable.ic_favorite_purple);
//                    isFavourite = true;
//                    saveState(isFavourite);
//                }
//            }

//            private void saveState(boolean isFavourite) {
//                SharedPreferences aSharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(
//                        "Favourite", Context.MODE_PRIVATE);
//                SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences
//                        .edit();
//                aSharedPreferencesEdit.putBoolean("State", isFavourite);
//                aSharedPreferencesEdit.apply();
//            }
//
//            private boolean readState() {
//                SharedPreferences aSharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(
//                        "Favourite", Context.MODE_PRIVATE);
//                return aSharedPreferences.getBoolean("State", true);
//            }
//        });

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
        intent.putExtra("Programs", (Parcelable) programs.get(position));
        startActivity(intent);

    }
}
