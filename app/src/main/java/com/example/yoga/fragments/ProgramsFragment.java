package com.example.yoga.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramsFragment extends Fragment {

    public static final String TAG = "PROGTAG";

    public ProgramsFragment() {
        // Required empty public constructor
    }

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

}
