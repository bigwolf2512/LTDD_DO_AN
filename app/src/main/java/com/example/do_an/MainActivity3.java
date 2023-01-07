package com.example.do_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.do_an.features.feature_list_products.ListProductActivity;
import com.example.do_an.features.feature_shop_cart.ShopCartActivity;
import com.example.do_an.list_order.ListOrderActivity;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_page);
    }
    public void onOpeningListProductPage(View View){
        Intent intent = new Intent(MainActivity3.this, ListProductActivity.class);
        startActivity(intent);
    }
    public void onOpeningShopCartPage(View View){
        Intent intent = new Intent(MainActivity3.this, ShopCartActivity.class);
        startActivity(intent);
    }
    public void onOpeningListOrderActivity(View View){
        Intent intent = new Intent(MainActivity3.this, ListOrderActivity.class);
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