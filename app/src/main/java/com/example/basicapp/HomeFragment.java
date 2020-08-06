package com.example.basicapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fabAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rclHome);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //arraylist
        ArrayList<DataRecycleHome> dataRecycleHomes = new ArrayList<>();
        dataRecycleHomes.add(new DataRecycleHome("Thứ 2", "Mua Đồ Điện", "50000VND"));
        dataRecycleHomes.add(new DataRecycleHome("Thứ 3", "Mua Quần Áo", "300000VND"));
        //

        HomeAdapter homeAdapter = new HomeAdapter(dataRecycleHomes, getActivity());
        recyclerView.setAdapter(homeAdapter);
        fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_add_dialog_layout);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
        Dialog dialog = new Dialog(getActivity());

        return view;




    }
}
