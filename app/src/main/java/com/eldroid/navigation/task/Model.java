package com.eldroid.navigation.task;

public class Model {
    private String description;
    private boolean isCompleted;
    private int imageResource;

    // Constructor
    public Model(String description) {
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
