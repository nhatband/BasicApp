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
    private EditText edtPurpose, edtMoney;
    ArrayList<ContentData> ListcontentData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, container, false);
        //
        RecyclerView recyclerView = view.findViewById(R.id.rclHome);
        dataBase = new DataBase(getActivity().getBaseContext());
        btnSave = view.findViewById(R.id.btnSave);
        edtPurpose = view.findViewById(R.id.edtPurpose);
        edtMoney = view.findViewById(R.id.edtMoney);
        //
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //arraylist
        ListcontentData = dataBase.getdata();

        HomeAdapter homeAdapter = new HomeAdapter(ListcontentData, getActivity());
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
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentData contentData = createContent();
                        dataBase.addContent(contentData);
                    }
                });
                dialog.cancel();
            }
        });
        Dialog dialog = new Dialog(getActivity());
        return view;
    }

    private ContentData createContent() {

        String content = edtPurpose.getText().toString();
        String money = edtMoney.getText().toString();
        ContentData contentData = new ContentData(content, money);
        return contentData;
    }
}
