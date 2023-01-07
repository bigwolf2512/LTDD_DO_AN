package com.example.do_an.core_firebase_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public abstract class PostMethodFirebaseFirestore extends AppCompatActivity {
    public abstract android.content.Context setContext();
    public abstract String path();
    public abstract void callBack();
    public abstract void onSuccess();
    public abstract int layoutResID();
    public abstract Map<String, Object> arg() ;
    public abstract String getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        callBack();
    }

    public void onAddToCartButtonFromPostMethod(View view){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(path()).document(getId()).set(arg())
                .addOnSuccessListener(aVoid -> {
                    onSuccess();
                })
                .addOnFailureListener(e -> Toast.makeText(setContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show());
    }
}
