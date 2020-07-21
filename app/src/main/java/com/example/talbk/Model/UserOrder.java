package com.example.talbk.Model;

public class UserOrder {
    String id;
    private String order_date;
    private String totalprice;

    public UserOrder() {
    }

    public UserOrder(String id, String order_date, String totalprice) {
        this.id = id;
        this.order_date = order_date;
        this.totalprice = totalprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
