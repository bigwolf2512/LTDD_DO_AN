package com.example.do_an.features.feature_shop_cart;

import android.content.Context;
import android.widget.ListView;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.GetMethodFirebaseFirestore;
import com.example.do_an.models.ProductModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShopCartActivity extends GetMethodFirebaseFirestore {

    @Override
    public Context setContext() {
        return ShopCartActivity.this;
    }

    @Override
    public String path() {
        return "products";
    }

    @Override
    public void callBack(QuerySnapshot data, FirebaseFirestore db) {

        List< DocumentSnapshot > list = data.getDocuments();
        final ArrayList<ProductModel> arrayList = new ArrayList<>();

        for (DocumentSnapshot d : list) {
            ProductModel p = d.toObject(ProductModel.class);

            arrayList.add(p);

            CustomListCartAdapter customListAdapter = new CustomListCartAdapter(this, arrayList);

            // create the instance of the ListView to set the numbersViewAdapter
            ListView listView = findViewById(R.id.listViewCart);

            // set the numbersViewAdapter for ListView
            listView.setAdapter(customListAdapter);
        }
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_shop_cart;
    }
}


