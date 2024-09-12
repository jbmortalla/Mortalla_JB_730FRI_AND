package com.eldroid.todolist;

public class Model {
    private String taskDescription;
    private boolean isChecked;
    private int imageResource;

    public Model(String taskDescription, boolean isChecked, int imageResource) {
        this.taskDescription = taskDescription;
        this.isChecked = isChecked;
        this.imageResource = imageResource;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}

