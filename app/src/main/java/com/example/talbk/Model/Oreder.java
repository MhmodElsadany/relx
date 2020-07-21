package com.example.talbk.Model;

public class Oreder {
    String id;
    Product product;
    String price;
    float quantity;

    public Oreder() {
    }

    public Oreder(Product product, String price, float quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
