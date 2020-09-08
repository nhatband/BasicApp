package com.example.basicapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataBase;
import com.example.basicapp.Data.DataRecycleHome;
import com.example.basicapp.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<ContentData> ListcontentData;
    Context context;
    DataBase dataBase;
    Activity activity;

    public HomeAdapter(ArrayList<ContentData> contentData, Context context, Activity activity) {
        this.ListcontentData = contentData;
        this.context = context;
        this.activity = activity;

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
        dataBase = new DataBase(context);

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

