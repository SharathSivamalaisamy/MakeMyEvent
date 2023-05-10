package com.example.makemyeventfinal.Model;

import androidx.annotation.NonNull;

public class Favs {
    private String Eventname, Category, Description, Link;


    public Favs() {
    }

    public Favs(String eventname, String description, String link) {
        Eventname = eventname;

        Description = description;
        Link = link;
    }

    public String getEventname() {
        return Eventname;
    }

    public void setEventname(String eventname) {
        Eventname = eventname;
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


    @NonNull
    @Override
    public String toString() {
        return this.Eventname + "\n" + this.getDescription();
    }
}