package com.example.eventhub;

import java.io.Serializable;

public class Program implements Serializable {
    private String name;
    private String location;
    private String description;
    private String date;
    private String startTime;
    private String endTime;
    private String imageUrl;
    private String id;

    // Default constructor is needed for Firebase
    public Program() {
    }

    // Constructor
    public Program(String name, String location, String description, String date, String startTime, String endTime, String imageUrl) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getImageUrl() { return imageUrl; }

    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getId() {
        return id;
    }

    // Setter for the id field
    public void setId(String id) {
        this.id = id;
    }
}
