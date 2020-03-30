package com.sadhana.yoga.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sadhana.yoga.R;
import com.sadhana.yoga.activity.DetailsAsana;
import com.sadhana.yoga.adapter.AsanaAdapter;
import com.sadhana.yoga.interfaces.OnAsanaClickListener;
import com.sadhana.yoga.model.AsanaResponse;
import com.sadhana.yoga.model.Asanas;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AsanaFragment extends Fragment implements OnAsanaClickListener {

    RecyclerView recyclerView;
    TextView asanaCategory;
    AsanaAdapter asanaAdapter;
    ArrayList<Asanas> asanas = new ArrayList<>();
    Gson gson;

    public AsanaFragment() {}

    public static AsanaFragment newInstance(){
        AsanaFragment asanaFragment = new AsanaFragment();
        return asanaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asana, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        asanaCategory = view.findViewById(R.id.categoryText);
        recyclerView = view.findViewById(R.id.recycler_view_asana);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gson = new Gson();
        String json = loadJSONFromAsset();

        AsanaResponse asanaResponse = gson.fromJson(json, AsanaResponse.class);
        asanas = asanaResponse.getAsanas();

        asanaAdapter = new AsanaAdapter(asanas, this, getActivity());
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
    public void onAsanaClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsAsana.class);
        intent.putExtra("Asanas", asanas.get(position));
        startActivity(intent);
    }
}
