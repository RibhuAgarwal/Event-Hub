package com.example.eventhub;

public class Event {
    private String date;
    private String name;
    private String location;
    private String startime;
    private String endTime;

    // No-argument constructor
    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(Event.class)
    }

    // Constructor with arguments
    public Event(String date, String name, String location, String startime, String endTime) {
        this.date = date;
        this.name = name;
        this.location = location;
        this.startime = startime;
        this.endTime = endTime;
    }
    // Getters
    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getStartTime() {
        return startime;
    }

    public String getEndTime() {
        return endTime;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartTime(String startime) {
        this.startime = startime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
