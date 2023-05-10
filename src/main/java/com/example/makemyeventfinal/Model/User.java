package com.example.makemyeventfinal.Model;

public class User {
    String email, name, photo_url, user_id;

    public User(String email, String name, String photo_url, String user_id) {
        this.email = email;
        this.name = name;
        this.photo_url = photo_url;
        this.user_id = user_id;
    }
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
