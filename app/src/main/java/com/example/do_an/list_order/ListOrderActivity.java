package com.example.do_an.list_order;

import android.content.Context;
import android.widget.ListView;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.GetMethodFirebaseFirestore;
import com.example.do_an.models.OrderModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListOrderActivity extends GetMethodFirebaseFirestore {
    @Override
    public Context setContext() {
        return com.example.do_an.list_order.ListOrderActivity.this;
    }

    @Override
    public String path() {
        return "order";
    }

    @Override
    public void callBack(QuerySnapshot data, FirebaseFirestore db) {
        List<DocumentSnapshot> list = data.getDocuments();
        final ArrayList<OrderModel> arrayList = new ArrayList<>();

        for (DocumentSnapshot d : list) {
            OrderModel p = d.toObject(OrderModel.class);

            arrayList.add(p);

            CustomListOrderAdapter customListAdapter = new CustomListOrderAdapter(this, arrayList);

            // create the instance of the ListView to set the numbersViewAdapter
            ListView listView = findViewById(R.id.listViewOrder);

            // set the numbersViewAdapter for ListView
            listView.setAdapter(customListAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                assert p != null;
                OrderModel result =  arrayList.get(position);
            });
        }


    }

    @Override
    public int layoutResID() {
        return R.layout.activity_list_order;
    }

}
