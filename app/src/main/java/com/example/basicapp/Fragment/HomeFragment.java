package com.example.basicapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataBase;
import com.example.basicapp.Adapter.HomeAdapter;
import com.example.basicapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fabAdd;
    DataBase dataBase;
    private Button btnSave;
    public HomeAdapter homeAdapter;
    private EditText edtPurpose, edtMoney;
    private RecyclerView recyclerView;
    public ImageButton imbCancel, imbEdit;
    ArrayList<ContentData> ContentData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_add_dialog_layout);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = inflater.inflate(R.layout.home_layout, container, false);
        //initWidget
        btnSave = dialog.findViewById(R.id.btnSave);
        edtPurpose = dialog.findViewById(R.id.edtPurpose);
        edtMoney = dialog.findViewById(R.id.edtMoney);
        recyclerView = view.findViewById(R.id.rclHome);
        imbCancel = view.findViewById(R.id.imbCancel);
//        imbEdit = view.findViewById(R.id.imbEdit);
        //
        dataBase = new DataBase(getActivity().getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ContentData = dataBase.getAlldata();
        setHomeAdapter();

        fabAdd = view.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentData contentData = createContent();
                        if (contentData != null) {
                            dataBase.addContent(contentData);
                            dialog.cancel();
                            edtPurpose.setText("");
                            edtMoney.setText("");
                        }
                        ContentData.clear();
                        ContentData.addAll(dataBase.getAlldata());
                        setHomeAdapter();
                    }
                });
            }
        });

        setHomeAdapter();
        return view;
    }

    private ContentData createContent() {
        String content = edtPurpose.getText().toString();
        String money = edtMoney.getText().toString();
        ContentData contentData = new ContentData(content, money);
        return contentData;
    }

    private void setHomeAdapter() {
        if (homeAdapter == null) {
            HomeAdapter homeAdapter = new HomeAdapter(ContentData, getActivity(), getActivity().getParent());
            recyclerView.setAdapter(homeAdapter);
        } else {
            homeAdapter.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);

        }
    }
}
