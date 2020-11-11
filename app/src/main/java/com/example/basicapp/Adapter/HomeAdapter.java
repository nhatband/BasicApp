package com.example.basicapp.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicapp.Data.ContentData;
import com.example.basicapp.Data.DataBase;
import com.example.basicapp.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<ContentData> ListcontentData;
    Context context;
    DataBase dataBase;
    Activity activity;
    ContentData contentData;

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
        contentData = ListcontentData.get(position);
        holder.imgID.getImageAlpha();
        holder.txtContent.setText(contentData.getmContent());
        holder.txtMoney.setText(contentData.getmMoney());
        dataBase = new DataBase(context);

    }

    @Override
    public int getItemCount() {
        return ListcontentData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtContent, txtMoney;
        ImageButton imbEdit, imbCancel;
        ImageView imgID;
        Dialog dialog;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgID = itemView.findViewById(R.id.imgID);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtMoney = itemView.findViewById(R.id.txtMoney);
            imbEdit = itemView.findViewById(R.id.imbEdit);
            imbCancel = itemView.findViewById(R.id.imbCancel);
            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.custom_add_dialog_layout);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imbEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final EditText edtPurpose, edtMoney;
                    Button btnSave;
                    edtPurpose = dialog.findViewById(R.id.edtPurpose);
                    edtMoney = dialog.findViewById(R.id.edtMoney);
                    btnSave = dialog.findViewById(R.id.btnSave);
                    contentData = ListcontentData.get(getAdapterPosition());
                    edtPurpose.setText(contentData.getmContent());
                    edtMoney.setText(contentData.getmMoney());
                    dialog.show();
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            contentData.setmContent(edtPurpose.getText().toString());
                            contentData.setmMoney(edtMoney.getText().toString());
                            int result = dataBase.updateNote(contentData);
                            if (result > 0) {
                                Toast.makeText(view.getContext(), "Update success", Toast.LENGTH_SHORT).show();
                                ListcontentData.clear();
                                ListcontentData.addAll(dataBase.getAlldata());
                                notifyDataSetChanged();
                                dialog.cancel();

                            } else {
                                Toast.makeText(view.getContext(), "Update fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            imbCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contentData = ListcontentData.get(getAdapterPosition());
                    int result = dataBase.deleteStudent(contentData.getmID());
                    if (result>0){
                        Toast.makeText(v.getContext(),"Delete Success",Toast.LENGTH_SHORT).show();
                        ListcontentData.clear();
                        ListcontentData.addAll(dataBase.getAlldata());
                        notifyDataSetChanged();
                    }else {
                        Toast.makeText(v.getContext(),"Delete fail",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

