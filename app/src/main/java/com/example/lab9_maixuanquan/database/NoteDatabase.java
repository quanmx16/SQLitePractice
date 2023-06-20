package com.example.lab9_maixuanquan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab9_maixuanquan.item.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    public static final String NOTE_TABLE_NAME = "note";
    public static final String TABLE_TASK = "Task";
    public static final String COLUMN_NAME_TASK_ID = "id";
    public static final String COLUMN_NAME_TASK_NAME = "name";

    public NoteDatabase(@Nullable Context context) {
        super(context, NOTE_TABLE_NAME, null, 1);
    }

    public long insertTask(TaskItem taskItem) {
        if (taskItem == null) {
            return 0;
        } else {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NAME_TASK_NAME, taskItem.getName());
            return db.insert(TABLE_TASK, null, cv);
        }
    }

    public long deleteTask(int id) {
        if (id <= 0) {
            return 0;
        } else {
            SQLiteDatabase db = getWritableDatabase();
            return db.delete(TABLE_TASK, COLUMN_NAME_TASK_ID + " = " + id, null);
        }
    }

    public long updateTask(TaskItem taskItem) {
        if (taskItem.getId() <= 0) {
            return 0;
        } else {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_NAME_TASK_NAME, taskItem.getName());
            return db.update(TABLE_TASK, cv, COLUMN_NAME_TASK_ID + " = " + taskItem.getId(), null);
        }
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public List<TaskItem> getAllTasks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME_TASK_ID + " ," + COLUMN_NAME_TASK_NAME + " FROM " + TABLE_TASK, null);
        List<TaskItem> tasks = null;
        while (cursor.moveToNext()) {
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
            tasks.add(new TaskItem(cursor.getInt(0), cursor.getString(1)));
        }
        return tasks;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TASK + " (" + COLUMN_NAME_TASK_ID + " Integer Primary Key Autoincrement, " + COLUMN_NAME_TASK_NAME + " nvarchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
