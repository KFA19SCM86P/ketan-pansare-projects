package com.example.newsgateway;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DrawerViewHolder extends RecyclerView.ViewHolder {

    TextView source_name;
    public DrawerViewHolder(@NonNull View itemView) {
        super(itemView);
        source_name = itemView.findViewById(R.id.source_name);
    }
}
