package com.example.lab9_maixuanquan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab9_maixuanquan.MainActivity;
import com.example.lab9_maixuanquan.R;
import com.example.lab9_maixuanquan.item.TaskItem;
import com.example.lab9_maixuanquan.viewholder.TaskItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemViewHolder> {
    private List<TaskItem> taskItems;
    private Context context;

    public TaskItemAdapter(List<TaskItem> taskItems, Context context) {
        this.taskItems = taskItems;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new TaskItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemViewHolder holder, int position) {
        TaskItem taskItem = taskItems.get(position);
        holder.getTaskName().setText(taskItem.getName());

        holder.getImgEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).editDialog(taskItem);
            }
        });
        holder.getImgDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).deleteDialog(taskItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskItems == null ? 0 : taskItems.size();
    }
}
