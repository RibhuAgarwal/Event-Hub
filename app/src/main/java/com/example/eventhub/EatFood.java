package com.example.eventhub;

public class EatFood {
    private int imageResId;  // Drawable resource id for the image
    private String title;    // Name of the food/place

    // Constructor
    public EatFood(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    // Getter for imageResId
    public int getImageResId() {
        return imageResId;
    }

    // Setter for imageResId
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }
}

