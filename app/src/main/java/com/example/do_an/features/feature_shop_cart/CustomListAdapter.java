package com.example.do_an.features.feature_shop_cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.do_an.R;
import com.example.do_an.models.ProductModel;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<ProductModel> {
    Context context;
    ArrayList<ProductModel> listData;
    int layoutResource;

    public CustomListAdapter(Context context, int layoutResource, ArrayList<ProductModel> objects){
        super(context, layoutResource, objects);
        this.context = context;
        this.layoutResource = layoutResource;
        this.listData = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(layoutResource, null);

        TextView textView1 = (TextView)convertView.findViewById(R.id.textViewName);
        textView1.setText(listData.get(position).getName());

        TextView textView2 = (TextView)convertView.findViewById(R.id.textViewPrice);
        textView2.setText(listData.get(position).getPrice());

        return convertView;
    }
}