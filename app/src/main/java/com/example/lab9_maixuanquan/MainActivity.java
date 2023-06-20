package com.example.lab9_maixuanquan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab9_maixuanquan.adapter.TaskItemAdapter;
import com.example.lab9_maixuanquan.database.NoteDatabase;
import com.example.lab9_maixuanquan.item.TaskItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteDatabase database;
    private RecyclerView rvTaskItems;
    private Button btnAddTask;
    private Button btnExitAddTask;
    private TextView edtTaskName;
    private Button btnEditTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTaskItems = findViewById(R.id.rvTaskItems);
        loadTaskList();
    }

    private void loadTaskList() {
        database = new NoteDatabase(this);
        TaskItemAdapter taskItemAdapter = new TaskItemAdapter(database.getAllTasks(), this);
        rvTaskItems.setAdapter(taskItemAdapter);
        rvTaskItems.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            addDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_task_dialog);
        btnAddTask = dialog.findViewById(R.id.btnAddTask);
        btnExitAddTask = dialog.findViewById(R.id.btnExit);
        edtTaskName = dialog.findViewById(R.id.etTaskName);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTaskName.getText() == null || edtTaskName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                } else {
                    TaskItem taskItem = new TaskItem();
                    taskItem.setName(edtTaskName.getText().toString());
                    database.insertTask(taskItem);
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadTaskList();
                }
            }
        });
        dialog.show();
        btnExitAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void deleteDialog(TaskItem taskItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa công việc " + taskItem.getName() + " không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.deleteTask(taskItem.getId());
                Toast.makeText(MainActivity.this, "Đã xóa " + taskItem.getName(), Toast.LENGTH_SHORT).show();
                loadTaskList();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void editDialog(TaskItem taskItem) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.edit_task_dialog);
        btnEditTask = dialog.findViewById(R.id.btnEditTask);
        btnExitAddTask = dialog.findViewById(R.id.btnExit);
        edtTaskName = dialog.findViewById(R.id.etTaskName);
        edtTaskName.setText(taskItem.getName());
        btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTaskName.getText() == null || edtTaskName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                } else {
                    taskItem.setName(edtTaskName.getText().toString());
                    database.updateTask(taskItem);
                    Toast.makeText(MainActivity.this, "Đã sửa", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadTaskList();
                }
            }
        });
        dialog.show();
        btnExitAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task, menu);
        return true;
    }
}