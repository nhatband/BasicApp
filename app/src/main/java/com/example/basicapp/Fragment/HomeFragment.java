package com.example.basicapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataBase;
import com.example.basicapp.Data.DataRecycleHome;
import com.example.basicapp.Adapter.HomeAdapter;
import com.example.basicapp.Model.Content;
import com.example.basicapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fabAdd;
    DataBase dataBase;
    private Button btnSave;
//     HomeAdapter homeAdapter;
    private EditText edtPurpose, edtMoney;
    private RecyclerView recyclerView;
    ArrayList<ContentData> ListcontentData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        View view = inflater.inflate(R.layout.home_layout, container, false);
        //
        recyclerView = view.findViewById(R.id.rclHome);
        dataBase = new DataBase(getActivity().getBaseContext());

        //
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //arraylist
        ListcontentData = dataBase.getdata();
        final HomeAdapter homeAdapter = new HomeAdapter(ListcontentData, getActivity(),getActivity().getParent());
        recyclerView.setAdapter(homeAdapter);
        fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_add_dialog_layout);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                btnSave = dialog.findViewById(R.id.btnSave);
                edtPurpose = dialog.findViewById(R.id.edtPurpose);
                edtMoney = dialog.findViewById(R.id.edtMoney);
                dialog.show();
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentData contentData = createContent();
                        if (contentData != null) {
                            dataBase.addContent(contentData);
                            dialog.cancel();
                        }
                        ListcontentData.clear();
                        ListcontentData.addAll(dataBase.getdata());
                        homeAdapter.notifyDataSetChanged();

                    }
                });
            }
        });
        return view;
    }

    private ContentData createContent() {
        String content = edtPurpose.getText().toString();
        String money = edtMoney.getText().toString();
        ContentData contentData = new ContentData(content, money);
        return contentData;
    }

//    private void setHomeAdapter() {
//        if (homeAdapter == null) {
//            HomeAdapter homeAdapter = new HomeAdapter(ListcontentData, getActivity(), getActivity().getParent());
//            recyclerView.setAdapter(homeAdapter);
//        } else {
//            homeAdapter.notifyDataSetChanged();
//        }
//    }
}
