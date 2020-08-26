package com.example.basicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataRecycleHome;
import com.example.basicapp.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<ContentData> ListcontentData;
    Context context;

    public HomeAdapter(ArrayList<ContentData> contentData, Context context) {
        this.ListcontentData = contentData;
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
        ContentData contentData = ListcontentData.get(position);
        holder.txtDate.setText(String.valueOf(contentData.getmID()));
        holder.txtContent.setText(contentData.getmContent());
        holder.txtMoney.setText(contentData.getmMoney());

    }

    @Override
    public int getItemCount() {
        return ListcontentData.size();
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
