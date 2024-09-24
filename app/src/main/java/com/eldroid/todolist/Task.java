package com.eldroid.todolist;

public class Task {
    private String description;
    private boolean isCompleted;
    private int imageResource;

    // Constructor
    public Task(String description, boolean isCompleted, int imageResource) {
        this.description = description;
        this.isCompleted = isCompleted;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getImageResource() {
        return imageResource;
    }

}

