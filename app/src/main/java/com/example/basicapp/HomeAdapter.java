package com.example.basicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<DataRecycleHome> dataRecycleHomes;
    Context context;

    public HomeAdapter(ArrayList<DataRecycleHome> dataRecycleHomes, Context context) {
        this.dataRecycleHomes = dataRecycleHomes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.home_recycleview_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtDate.setText(dataRecycleHomes.get(position).getDate());
        holder.txtContent.setText(dataRecycleHomes.get(position).getContent());
        holder.txtMoney.setText(dataRecycleHomes.get(position).money);
    }

    @Override
    public int getItemCount() {
        return dataRecycleHomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtContent, txtMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtMoney = itemView.findViewById(R.id.txtMoney);

        }
    }
}
