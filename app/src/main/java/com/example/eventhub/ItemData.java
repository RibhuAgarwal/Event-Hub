package com.example.eventhub;
public class ItemData {
    private int imageResource; // For simplicity, I'm using resource IDs. You can use URLs or other data types if needed.
    private String title;
    private String location;

    public ItemData(int imageResource, String title, String location) {
        this.imageResource = imageResource;
        this.title = title;
        this.location = location;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
