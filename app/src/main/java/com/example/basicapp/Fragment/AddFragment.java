package com.example.basicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataBase;
import com.example.basicapp.R;

public class AddFragment extends Fragment {
    DataBase dataBase;
    private Button btnSave;
    private EditText edtPurpose, edtMoney;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_add_dialog_layout, container, false);
        dataBase = new DataBase(getActivity().getBaseContext());
        btnSave = view.findViewById(R.id.btnSave);
        edtPurpose = view.findViewById(R.id.edtPurpose);
        edtMoney = view.findViewById(R.id.edtMoney);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentData contentData = createContent();
                if (contentData != null) {
                    dataBase.addContent(contentData);
                }

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
}
