package com.eldroid.navigation.info;

public class Model {
    private String name;
    private String email;
    private String gender;
    private String interest;

    private static Model instance;

    // Private constructor to enforce singleton pattern
    private Model() {
        // Set default values
        name = "John Doe";
        email = "john.doe@example.com";
        gender = "Not Specified";  // Default gender
        interest = "";             // Default interest
    }

    // Singleton instance getter
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    // Getters and setters for the profile data
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
