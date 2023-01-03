package com.example.do_an.features.feature_shop_cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.do_an.R;
import com.example.do_an.models.ProductModel;

import java.util.ArrayList;

public class ShopCartActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ProductModel> arrayList;
    CustomListAdapter customListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        listView = (ListView)findViewById(R.id.listViewCart);

        arrayList = new ArrayList<>();
        arrayList.add(new ProductModel("123", 123, "1", "2",3));
        arrayList.add(new ProductModel("124", 123, "1", "2",3));
        arrayList.add(new ProductModel("125", 123, "1", "2",3));
        arrayList.add(new ProductModel("126", 123, "1", "2",3));
        arrayList.add(new ProductModel("127", 123, "1", "2",3));
        arrayList.add(new ProductModel("128", 123, "1", "2",3));

        customListAdapter = new CustomListAdapter(ShopCartActivity.this, R.layout.activity_shop_cart_item,arrayList);

        listView.setAdapter(customListAdapter);
    }
}