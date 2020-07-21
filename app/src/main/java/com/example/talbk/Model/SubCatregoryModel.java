package com.example.talbk.Model;

public class SubCatregoryModel {
    private String id;
    private String name;
    private String image;
    private String duration;
    private String price;
    private String Category;

    public SubCatregoryModel(String id, String name, String image, String duration, String price, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.duration = duration;
        this.price = price;
        Category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
