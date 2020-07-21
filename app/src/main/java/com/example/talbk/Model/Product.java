package com.example.talbk.Model;

public class Product {
    private String id;
    private String name;
    private String descreption;
    private String image;
    private String price;
    private String type;


    public Product() {
    }

    public Product(String id, String name, String descreption, String price, String image, String type) {
        this.id = id;
        this.name = name;
        this.descreption = descreption;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
