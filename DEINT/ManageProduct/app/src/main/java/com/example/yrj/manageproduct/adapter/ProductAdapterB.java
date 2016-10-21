package com.example.yrj.manageproduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yrj.manageproduct.LoginApplication;
import com.example.yrj.manageproduct.R;
import com.example.yrj.manageproduct.model.Product;

/**
 * Created by usuario on 21/10/16.
 */

public class ProductAdapterB extends ArrayAdapter<Product> {
    //private Context context;
    private int[] image;

    public ProductAdapterB(Context context) {
        super(context, R.layout.listrow,((LoginApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        TextView tvName;
        TextView tvStock;
        TextView tvPrice;
        ImageView img;
        if (item == null) {
            //1.Create an inflater object that it is initialized to the Context LayoutInflater
            //LayoutInflater inflater = LayoutInflater.from(context);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //2. Inflate the view. Create memory in View Object that contains XML widgets
            item = inflater.inflate(R.layout.listrow, null);
        }

        //3. Setting widgets
        img = (ImageView) item.findViewById(R.id.imgRow);
        tvName = (TextView) item.findViewById(R.id.tvNameRow);
        tvStock = (TextView) item.findViewById(R.id.tvStockRow);
        tvPrice = (TextView) item.findViewById(R.id.tvPriceRow);

        //Setting data adapter to widgets
        img.setImageResource(getItem(position).getImage());
        tvName.setText(getItem(position).getName());
        tvStock.setText("Stock: " + getItem(position).getFormatedUnitsInStock());
        tvPrice.setText("Precio: " + getItem(position).getFormatedPrice());
        return item;
    }
}
