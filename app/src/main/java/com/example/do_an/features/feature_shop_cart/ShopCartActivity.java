package com.example.do_an.features.feature_shop_cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.GetMethodFirebaseFirestore;
import com.example.do_an.features.feature_list_products.ListProductDetailActivity;
import com.example.do_an.models.ProductModel;
import com.example.do_an.models.UserModel;
import com.example.do_an.share.Debugger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShopCartActivity extends GetMethodFirebaseFirestore {

    int index;
    UserModel userModel;

    @Override
    public Context setContext() {
        return ShopCartActivity.this;
    }

    @Override
    public String path() {
        return "carts";
    }

    @Override
    public void callBack(QuerySnapshot data, FirebaseFirestore db) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Map<String, Object> map = new HashMap<>();
        List<DocumentSnapshot> listDoc = data.getDocuments();
        Gson gson = new Gson();

        for (DocumentSnapshot d : listDoc) {
            if (d.getId().equals(auth.getUid())) {
                map = d.getData();
            }
        }

        assert map != null;
        JSONObject json = new JSONObject(map);

        userModel = gson.fromJson(json.toString(), UserModel.class);

        ArrayList<ProductModel> arrayList = userModel.getItems();

        CustomListCartAdapter customListAdapter = new CustomListCartAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView listView = findViewById(R.id.listViewCart);

        // set the numbersViewAdapter for ListView
        if(arrayList != null){
            listView.setAdapter(customListAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                index = position;
                Debugger.log(index);
                onIncreaseQuantityCartButton(view);
                onDecreaseQuantityCartButton(view);
            });
        }

    }

    public void onOpeningListProductsDetail(String id, String name, String description, String image, long price, long quantity) {
        Intent intent = new Intent(ShopCartActivity.this, ListProductDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("image", image);
        intent.putExtra("price", price);
        intent.putExtra("quantity", quantity);
        startActivity(intent);
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_shop_cart;
    }

    @SuppressLint("SetTextI18n")
    public void onIncreaseQuantityCartButton(View view) {
        ArrayList<ProductModel> arrayList = userModel.getItems();
        long qty = userModel.getItems().get(index).getQuantity();
        qty += 1;
        ProductModel p = arrayList.get(index);
        p.setQuantity(qty);
        arrayList.set(index, p);

        CustomListCartAdapter customListAdapter = new CustomListCartAdapter(this, arrayList);
        ListView listView = findViewById(R.id.listViewCart);
        listView.setAdapter(customListAdapter);
    }

    @SuppressLint("SetTextI18n")
    public void onDecreaseQuantityCartButton(View view) {

    }

    public long onCheckQuantity(long quantity) {
        return Math.max(quantity, 1);
    }

    public ArrayList<ProductModel> sortList(ArrayList<ProductModel> list) {

        ArrayList<String> listId = new ArrayList<>();


        for (ProductModel p : list) {
            listId.add(p.getId());
        }


        Collections.sort(listId);

        ArrayList<ProductModel> _list =  new ArrayList<>();

        for (String s : listId) {
            for (ProductModel p : list) {
                if (Objects.equals(p.getId(), s)) {
                    _list.add(p);
                }
            }
        }
        return _list;

    }
}


