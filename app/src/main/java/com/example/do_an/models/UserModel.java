package com.example.do_an.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class UserModel{
    @JsonProperty("items")
    public ArrayList<ProductModel> getItems() {
        return this.items; }
    public void setItems(ArrayList<ProductModel> items) {
        this.items = items; }
    ArrayList<ProductModel> items;

    @JsonProperty("userId")
    public String getUserId() {
        return this.userId; }
    public void setUserId(String userId) {
        this.userId = userId; }
    String userId;
}

