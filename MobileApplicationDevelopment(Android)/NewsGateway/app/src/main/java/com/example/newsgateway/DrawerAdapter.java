package com.example.newsgateway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class DrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder> {

    private MainActivity mainActivity;
    private List<Source> sourceList;
    private HashMap<String, Integer> categoryColorMap = new HashMap<>();

    public DrawerAdapter(MainActivity mainActivity, List<Source> sourceList) {
        this.mainActivity = mainActivity;
        this.sourceList = sourceList;
    }

    @NonNull
    @Override
    public DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_list_item, parent, false);

         itemView.setOnClickListener(mainActivity);

        return new DrawerViewHolder(itemView);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onBindViewHolder(@NonNull DrawerViewHolder holder, int position) {
        Source source = sourceList.get(position);
        holder.source_name.setText(source.getName());

        if(!categoryColorMap.isEmpty() && categoryColorMap.containsKey(source.getCategory())) {
            holder.source_name.setTextColor(categoryColorMap.get(source.getCategory()));
        }
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public void setCategoryColorMap(HashMap<String, Integer> categoryColorMap) {
        this.categoryColorMap = categoryColorMap;
    }
}
