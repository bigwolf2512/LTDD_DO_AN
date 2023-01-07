package com.example.do_an.features.feature_shop_cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.GetMethodFirebaseFirestore;
import com.example.do_an.models.UserModel;
import com.example.do_an.share.Debugger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCartActivity extends GetMethodFirebaseFirestore {

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

        UserModel _userModel = gson.fromJson(json.toString(), UserModel.class);

        CustomListCartAdapter customListAdapter = new CustomListCartAdapter(this, _userModel.getItems());

        // create the instance of the ListView to set the numbersViewAdapter
        ListView listView = findViewById(R.id.listViewCart);

        // set the numbersViewAdapter for ListView
        listView.setAdapter(customListAdapter);
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_shop_cart;
    }


}


