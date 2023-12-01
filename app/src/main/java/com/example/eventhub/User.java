package com.example.eventhub;
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    // Default constructor for Firebase
    public User() {
    }

    // Parametrized Constructor
    public User(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    // Getter for the email field
    public String getEmail() {
        return email;
    }

    // Setter for the email field
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for the firstName field
    public String getFirstName() {
        return firstName;
    }

    // Setter for the firstName field
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for the lastName field
    public String getLastName() {
        return lastName;
    }

    // Setter for the lastName field
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter for the phone field
    public String getPhone() {
        return phone;
    }

    // Setter for the phone field
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
