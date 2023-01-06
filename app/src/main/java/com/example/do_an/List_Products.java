package com.example.do_an;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class List_Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_item);

    }




    public void user(View view){
        Intent intent = new Intent(List_Products.this, MainActivity3.class);
        startActivity(intent);
    }
    public void quanly(View view){
        Intent intent = new Intent(List_Products.this, MainActivity4.class);
        startActivity(intent);
    }
    public void setting(View view){
        Intent intent = new Intent(List_Products.this, MainActivity5.class);
        startActivity(intent);
    }
}