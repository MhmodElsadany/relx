package com.example.talbk.Model;

import java.util.ArrayList;

public class Check {

    String userid;
    ArrayList<ProductsCheck> products;
    String total_pricce;
    String description;

    public Check() {
    }

    public Check(String userid, ArrayList<ProductsCheck> products, String description, String total_pricce) {
        this.userid = userid;
        this.products = products;
        this.description = description;
        this.total_pricce = total_pricce;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ArrayList<ProductsCheck> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductsCheck> products) {
        this.products = products;
    }

    public String getTotal_pricce() {
        return total_pricce;
    }

    public void setTotal_pricce(String total_pricce) {
        this.total_pricce = total_pricce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

