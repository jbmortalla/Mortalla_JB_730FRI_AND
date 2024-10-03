package com.eldroid.navigation.info;

public class Adapter {
    private Model model;

    public Adapter() {
        // Get the Model instance
        this.model = Model.getInstance();
    }

    // Method to update profile data
    public void updateProfile(String name, String email, String gender, String interest) {
        model.setName(name);
        model.setEmail(email);
        model.setGender(gender);
        model.setInterest(interest);
    }

    // Method to get profile data
    public Model getProfile() {
        return model;
    }
}
