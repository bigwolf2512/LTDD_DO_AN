package com.example.do_an.features.feature_list_products;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.do_an.R;
import com.example.do_an.core_firebase_crud.PostMethodFirebaseFirestore;
import com.example.do_an.features.feature_shop_cart.ShopCartActivity;
import com.example.do_an.models.ProductModel;
import com.example.do_an.models.UserModel;
import com.example.do_an.share.Debugger;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ListProductDetailActivity extends PostMethodFirebaseFirestore {
    int quantity = 1;
    ProductModel data;

    @Override
    public String path() {
        return "carts";
    }

    @Override
    public String getId() {
        FirebaseAuth authProfile = FirebaseAuth.getInstance();
        return authProfile.getUid();
    }

    @Override
    public synchronized Map<String, Object> arg() {

        Map<String, Object> map = new HashMap<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        ArrayList<ProductModel> arrayList = new ArrayList<>();

        Task<DocumentSnapshot> doc = db.collection("carts")
                .document(Objects.requireNonNull(auth.getUid()))
                .get();

        doc.addOnSuccessListener(queryDocumentSnapshots -> {
            UserModel _userModel = null;
            Gson gson = new Gson();
            JSONObject json = new JSONObject(Objects.requireNonNull(queryDocumentSnapshots.getData()));

            _userModel = gson.fromJson(json.toString(), UserModel.class);

            Debugger.log("bbbbb");
            Debugger.log(json);
            Debugger.log(_userModel.getUserId());
            Debugger.log(_userModel.getItems());
        });

        arrayList.add(data);

        map.put("userId", auth.getUid());
        map.put("items", arrayList);
        return map;
    }

    public void onAddToCartButton(View view) {
        onAddToCartButtonFromPostMethod(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void callBack() {
        Intent intent = getIntent(); // gets the previously created intent
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        long price = intent.getLongExtra("price", 0);
        data = new ProductModel(id, name, price, image, description, quantity);

        TextView textViewProductDetailName = findViewById(R.id.textViewProductDetailName);
        textViewProductDetailName.setText(name);

        TextView textViewProductDetailPrice = findViewById(R.id.textViewProductDetailPrice);
        textViewProductDetailPrice.setText("Giá của sản phẩm: " + price + "đ");

        TextView textViewProductDetailDescription = findViewById(R.id.textViewProductDetailDescription);
        textViewProductDetailDescription.setText(description);

        ImageView imageViewProductDetail = findViewById(R.id.imageViewProductDetail);
        Picasso.get().load(image).into(imageViewProductDetail);

        TextView textViewProductDetailQuantity = findViewById(R.id.textViewProductDetailQuantity);
        textViewProductDetailQuantity.setText(Integer.toString(quantity));
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(ListProductDetailActivity.this, ShopCartActivity.class);
        startActivity(intent);
        Toast.makeText(setContext(), "Added " + data.getQuantity() + " " + data.getName() + " success.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_list_products_detail;
    }

    @Override
    public Context setContext() {
        return ListProductDetailActivity.this;
    }

    @SuppressLint("SetTextI18n")
    public void onIncreaseQuantityButton(View view) {
        int qty = quantity += 1;
        quantity = onCheckQuantity(qty);
        data.setQuantity(quantity);
        TextView textViewProductDetailQuantity = findViewById(R.id.textViewProductDetailQuantity);
        textViewProductDetailQuantity.setText(Integer.toString(quantity));
    }

    @SuppressLint("SetTextI18n")
    public void onDecreaseQuantityButton(View view) {
        int qty = quantity -= 1;
        quantity = onCheckQuantity(qty);
        data.setQuantity(quantity);
        TextView textViewProductDetailQuantity = findViewById(R.id.textViewProductDetailQuantity);
        textViewProductDetailQuantity.setText(Integer.toString(quantity));
    }

    public int onCheckQuantity(int quantity) {
        return Math.max(quantity, 1);
    }


}
