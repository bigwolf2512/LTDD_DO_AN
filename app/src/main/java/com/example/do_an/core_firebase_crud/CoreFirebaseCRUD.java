package com.example.do_an.core_firebase_crud;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.do_an.R;
import com.example.do_an.features.feature_shop_cart.CustomListAdapter;
import com.example.do_an.models.ProductModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreFirebaseCRUD extends AppCompatActivity {
    public abstract android.content.Context setContext();
    public abstract String path();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        // create a arraylist of the type NumbersView
        final ArrayList<ProductModel> arrayList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(path()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                for (DocumentSnapshot d : list) {
                    ProductModel p = d.toObject(ProductModel.class);

                    arrayList.add(p);

                    CustomListAdapter customListAdapter = new CustomListAdapter(this, arrayList);

                    // create the instance of the ListView to set the numbersViewAdapter
                    ListView listView = findViewById(R.id.listViewCart);

                    // set the numbersViewAdapter for ListView
                    listView.setAdapter(customListAdapter);
                }
                Toast.makeText(setContext(), "Data Loaded Success", Toast.LENGTH_SHORT).show();
            } else {
                // if the snapshot is empty we are displaying a toast message.
                Toast.makeText(setContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            // if we do not get any data or any error we are displaying
            // a toast message that we do not get any data
            Toast.makeText(setContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
        });



    }

}
