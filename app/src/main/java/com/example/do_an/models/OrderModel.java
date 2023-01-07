package com.example.do_an.models;

public class OrderModel {

    public OrderModel(){}

    public OrderModel(String id, String name, long price, long pay, String image, long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pay = pay;
        this.image = image;
        this.quantity = quantity;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPay() {
        return pay;
    }

    public void setPay(long pay) {
        this.pay = pay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    private String id;
    private String name;
    private long price;
    private long pay;
    private String image;
    private long quantity;
}
