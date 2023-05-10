package com.example.makemyeventfinal.Model;

import java.util.List;

public class UserCart {
    String user_id, name, email, photo_url;
    List<String> cart ;

    public UserCart(String user_id, String name, String email, String photo_url, List<String> cart) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.photo_url = photo_url;
        this.cart = cart;
    }

    public UserCart(){

    }

    public UserCart(String id, String displayName, String email, String valueOf, List<String> emptyList, List<String> emptyList2, List<String> emptyList3) {
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto_url() {
        return photo_url;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public List<String> getCart() {
        return cart;
    }
}