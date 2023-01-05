package com.example.do_an.models;

public class ProductModel {
    public ProductModel(
        String name , int price, String img, String description,int quantity
    ) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    String name;
    int price;
    String img;
    String description;
    int quantity;



}
