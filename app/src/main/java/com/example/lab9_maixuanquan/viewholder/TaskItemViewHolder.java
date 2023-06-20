package com.example.lab9_maixuanquan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab9_maixuanquan.R;

public class TaskItemViewHolder extends RecyclerView.ViewHolder {
    private TextView taskName;
    private ImageView imgEdit;
    private ImageView imgDelete;

    public TaskItemViewHolder(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.tvTaskName);
        imgEdit = itemView.findViewById(R.id.imgEdit);
        imgDelete = itemView.findViewById(R.id.imgDelete);
    }

    public TextView getTaskName() {
        return taskName;
    }

    public ImageView getImgEdit() {
        return imgEdit;
    }

    public ImageView getImgDelete() {
        return imgDelete;
    }
}
