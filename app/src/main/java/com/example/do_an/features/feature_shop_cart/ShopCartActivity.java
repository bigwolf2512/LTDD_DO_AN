package com.example.do_an.features.feature_shop_cart;

import android.content.Context;

import com.example.do_an.core_firebase_crud.CoreFirebaseCRUD;

public class ShopCartActivity extends  CoreFirebaseCRUD {
    @Override
    public Context setContext() {
        return ShopCartActivity.this;
    }

    @Override
    public String path() {
        return "products";
    }
}


