package com.example.eventhub;

public class EatData  {
    private int imageResource; // For simplicity, I'm using resource IDs. You can use URLs or other data types if needed.
    private String title;


    public EatData(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
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


}