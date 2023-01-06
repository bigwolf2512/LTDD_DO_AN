package com.example.do_an;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.do_an.features.feature_shop_cart.ShopCartActivity;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_page);
    }

    public void list_products(View View){
        Intent intent = new Intent(MainActivity3.this, List_Products.class);
        startActivity(intent);
    }
    public void onOpeningShopCartPage(View View){
        Intent intent = new Intent(MainActivity3.this, ShopCartActivity.class);
        startActivity(intent);
    }
    public void user(View view){
        Intent intent = new Intent(MainActivity3.this, MainActivity3.class);
        startActivity(intent);
    }
    public void quanly(View view){
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        startActivity(intent);
    }
    public void setting(View view){
        Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
        startActivity(intent);
    }
}