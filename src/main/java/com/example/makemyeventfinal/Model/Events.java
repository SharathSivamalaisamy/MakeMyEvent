package com.example.makemyeventfinal.Model;

import androidx.annotation.NonNull;

public class Events {
    private String Eventname, Category, Description, Link;
    private String mImageUrl;


    public Events() {
    }

    public Events(String eventname, String category, String description, String link) {
        Eventname = eventname;
        Category = category;
        Description = description;
        Link = link;

    }

    public String getEventname() {
        return Eventname;
    }

    public void setEventname(String eventname) {
        Eventname = eventname;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return this.Eventname + "\n" + this.getDescription();
    }
}