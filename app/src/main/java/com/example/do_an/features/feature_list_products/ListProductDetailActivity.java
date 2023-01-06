package com.example.do_an.features.feature_list_products;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.do_an.R;
import com.squareup.picasso.Picasso;


public class ListProductDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products_detail);

        Intent intent = getIntent(); // gets the previously created intent
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        long price = intent.getLongExtra("price", 0);

        TextView textViewProductDetailName = findViewById(R.id.textViewProductDetailName);
        textViewProductDetailName.setText(name);

        TextView textViewProductDetailPrice = findViewById(R.id.textViewProductDetailPrice);
        textViewProductDetailPrice.setText("Giá của sản phẩm: " + Long.toString(price) + "đ");

        TextView textViewProductDetailDescription = findViewById(R.id.textViewProductDetailDescription);
        textViewProductDetailDescription.setText(description);

        ImageView imageViewProductDetail = findViewById(R.id.imageViewProductDetail);
        Picasso.get()
                .load(image)
                .into(imageViewProductDetail);
    }
}
