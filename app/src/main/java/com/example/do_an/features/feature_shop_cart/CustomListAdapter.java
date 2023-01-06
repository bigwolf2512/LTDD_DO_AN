package com.example.do_an.features.feature_shop_cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.do_an.R;
import com.example.do_an.models.ProductModel;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<ProductModel> {

    // invoke the suitable constructor of the ArrayAdapter class
    public CustomListAdapter(@NonNull Context context, ArrayList<ProductModel> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_shop_cart_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ProductModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        ImageView imageViewItemCart = currentItemView.findViewById(R.id.imageViewItemCart);

        Picasso.get()
                .load(currentNumberPosition.getImage())
                .resize(84, 84)
                .centerCrop()
                .into(imageViewItemCart);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textViewName = currentItemView.findViewById(R.id.textViewName);
        textViewName.setText(currentNumberPosition.getName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textViewPrice = currentItemView.findViewById(R.id.textViewPrice);
        textViewPrice.setText(formatMoney(Long.toString(currentNumberPosition.getPrice())));

        // then return the recyclable view
        return currentItemView;
    }

    public String formatMoney(String money){

        return  money + "đ";
    }
}


