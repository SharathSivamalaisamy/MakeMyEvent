package com.example.makemyeventfinal.Model;

public class Upload {
    private String mName;
    private String  Category, Description, Link;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String category,String description,String link,String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        Category = category;
        Description = description;
        Link = link;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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
}