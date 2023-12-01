package com.example.eventhub;
public class Access {
    private int imageResId;  // Drawable resource id for the image
    private String title;    // Title (e.g., "Train Station")
    private String location; // Location info (e.g., "Pescadero Condado de San Mateo CA")

    // Constructor
    public Access(int imageResId, String title, String location) {
        this.imageResId = imageResId;
        this.title = title;
        this.location = location;
    }

    // Getters
    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
