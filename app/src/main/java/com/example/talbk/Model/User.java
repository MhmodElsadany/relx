package com.example.talbk.Model;

public class User {
    String id;
    String name;
    String phone;
    String address;
    String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User(String id, String name, String phone, String address, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public User(String name, String phone, String address, String password) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
