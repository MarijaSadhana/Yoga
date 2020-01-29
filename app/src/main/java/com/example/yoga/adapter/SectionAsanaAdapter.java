//package com.example.yoga.adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.yoga.R;
//import com.example.yoga.common.Constants;
//import com.example.yoga.fragments.AsanaFragment;
//import com.example.yoga.interfaces.OnItemListener;
//import com.example.yoga.model.Asana;
//import com.example.yoga.model.AsanaResponse;
//
//import java.util.ArrayList;
//
//public class SectionAsanaAdapter extends RecyclerView.Adapter<SectionAsanaAdapter.SectionViewHolder> {
//
//    ArrayList<AsanaResponse> asanaList;
//    LayoutInflater inflater;
//    Context context;
//    OnItemListener onItemListener;
//    int asanaCategory;
//
//    public SectionAsanaAdapter(ArrayList<AsanaResponse> asanaList, Context context, OnItemListener onItemListener) {
//        this.asanaList = asanaList;
//        this.inflater = LayoutInflater.from(context);
//        this.context = context;
//        this.onItemListener = onItemListener;
//    }
//
//    @NonNull
//    @Override
//    public SectionAsanaAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.recyclerview_asana_section, parent, false);
//        return new SectionViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SectionAsanaAdapter.SectionViewHolder holder, int position) {
//        final AsanaResponse asanaResponse = asanaList.get(position);
//        AsanaAdapter asanaAdapter = new AsanaAdapter(context, asanaResponse.getAsanas(), onItemListener);
//        if (holder.recyclerView == null){
//            Log.d("REC ERROR", "error");
//        }
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//        holder.recyclerView.setAdapter(asanaAdapter);
//        holder.seeAllButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemListener != null){
//                    onItemListener.onSeeAllClick(asanaResponse.getAsanaCategory(), asanaCategory);
//                }
//            }
//        });
//
//        switch (asanaResponse.getAsanaCategory()){
//            case Constants.BEGINNERS:
//                holder.sectionTitle.setText(context.getResources().getString(R.string.beginners));
//                break;
//            case Constants.INTERMEDIATE:
//                holder.sectionTitle.setText(context.getResources().getString(R.string.intermediate));
//                break;
//            case Constants.ADVANCED:
//                holder.sectionTitle.setText(context.getResources().getString(R.string.advanced));
//                break;
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return asanaList.size();
//    }
//
//    public class SectionViewHolder extends RecyclerView.ViewHolder {
//        TextView sectionTitle, seeAllButton;
//        RecyclerView recyclerView;
//
//        public SectionViewHolder(@NonNull View itemView) {
//            super(itemView);
//            sectionTitle = (TextView) itemView.findViewById(R.id.section_title);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_items);
//            seeAllButton = (TextView) itemView.findViewById(R.id.section_seeall_button);
//
//        }
//    }
//}
