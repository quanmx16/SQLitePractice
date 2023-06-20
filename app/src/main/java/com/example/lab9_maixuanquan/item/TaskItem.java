package com.example.lab9_maixuanquan.item;

public class TaskItem {
    private int id;
    private String name;

    public TaskItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TaskItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
