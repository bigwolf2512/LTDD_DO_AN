package com.example.do_an.core_firebase_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.do_an.models.ProductModel;
import com.example.do_an.models.UserModel;
import com.example.do_an.share.Debugger;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class PostMethodFirebaseFirestore extends AppCompatActivity {
    public abstract android.content.Context setContext();
    public abstract String path();
    public abstract void callBack();
    public abstract void onSuccess();
    public abstract int layoutResID();
    public abstract String getId();
    public abstract ProductModel data();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        callBack();
    }

    public void onAddToCartButtonFromPostMethod(View view) {
        Map<String, Object> map = new HashMap<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ArrayList<ProductModel> arrayList = new ArrayList<>();

        Task<DocumentSnapshot> doc = db.collection("carts")
                .document(Objects.requireNonNull(auth.getUid()))
                .get();

        doc.addOnSuccessListener(queryDocumentSnapshots -> {
            Debugger.log("123123");

            if(queryDocumentSnapshots.getData() != null){
                Gson gson = new Gson();
                JSONObject json = new JSONObject(queryDocumentSnapshots.getData());
                UserModel _userModel = gson.fromJson(json.toString(), UserModel.class);
                arrayList.addAll(_userModel.getItems());
            }

            arrayList.add(data());

            map.put("userId", auth.getUid());
            map.put("items", arrayList);

            db.collection(path()).document(getId()).set(map)
                    .addOnSuccessListener(aVoid -> {
                        onSuccess();
                    })
                    .addOnFailureListener(e -> Toast.makeText(setContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show());
        });
    }
}
