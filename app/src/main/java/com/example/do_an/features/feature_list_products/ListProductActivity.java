package com.example.do_an.features.feature_list_products;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.GetMethodFirebaseFirestore;
import com.example.do_an.models.ProductModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends GetMethodFirebaseFirestore {
    @Override
    public Context setContext() {
        return ListProductActivity.this;
    }

    @Override
    public String path() {
        return "products";
    }

    @Override
    public void callBack(QuerySnapshot data, FirebaseFirestore db) {
        List<DocumentSnapshot> list = data.getDocuments();
        final ArrayList<ProductModel> arrayList = new ArrayList<>();

        for (DocumentSnapshot d : list) {
            ProductModel p = d.toObject(ProductModel.class);

            arrayList.add(p);

            CustomListProductsAdapter customListAdapter = new CustomListProductsAdapter(this, arrayList);

            // create the instance of the ListView to set the numbersViewAdapter
            ListView listView = findViewById(R.id.listViewProducts);

            // set the numbersViewAdapter for ListView
            listView.setAdapter(customListAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                assert p != null;
                ProductModel result =  arrayList.get(position);
                onOpeningListProductsDetail(result.getId(), result.getName(),result.getDescription(),result.getImage(), result.getPrice(), result.getQuantity());
            });
        }


    }

    @Override
    public int layoutResID() {
        return R.layout.activity_list_products;
    }

    public void onOpeningListProductsDetail( String id, String name, String description, String image, long price,  long quantity){
        Intent intent = new Intent(ListProductActivity.this, ListProductDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("image", image);
        intent.putExtra("price", price);
        intent.putExtra("quantity",quantity );
        startActivity(intent);
    }
}
