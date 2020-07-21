package com.example.talbk.Model;

public class ProductsCheck {
    String productid;
    String QTY;
    String price;


    public ProductsCheck() {
    }

    public ProductsCheck(String productid, String price, String QTY) {
        this.productid = productid;
        this.QTY = QTY;
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
