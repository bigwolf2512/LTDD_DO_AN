package com.example.do_an.core_firebase_crud;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public abstract class GetMethodFirebaseFirestore extends AppCompatActivity {
    public abstract android.content.Context setContext();
    public abstract String path();
    public abstract void callBack(QuerySnapshot data, FirebaseFirestore db);
    public abstract int layoutResID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        // create a arraylist of the type NumbersView

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(path()).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                callBack(queryDocumentSnapshots, db);
             //   Toast.makeText(setContext(), "Data Loaded Success", Toast.LENGTH_SHORT).show();
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
