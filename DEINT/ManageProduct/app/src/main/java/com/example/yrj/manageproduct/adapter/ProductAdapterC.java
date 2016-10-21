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

public class ProductAdapterC extends ArrayAdapter<Product> {
    //private Context context;
    private int[] image;

    public ProductAdapterC(Context context) {
        super(context, R.layout.listrow,((LoginApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ProductHolder productHolder;
        if (item == null) {
            //1.Create an inflater object that it is initialized to the Context LayoutInflater
            //LayoutInflater inflater = LayoutInflater.from(context);
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //2. Inflate the view. Create memory in View Object that contains XML widgets
            item = inflater.inflate(R.layout.listrow, null);
            //3. Setting widgets
            productHolder = new ProductHolder();
            productHolder.img = (ImageView) item.findViewById(R.id.imgRow);
            productHolder.tvName = (TextView) item.findViewById(R.id.tvNameRow);
            productHolder.tvStock = (TextView) item.findViewById(R.id.tvStockRow);
            productHolder.tvPrice = (TextView) item.findViewById(R.id.tvPriceRow);
            item.setTag(productHolder);
        }else
            productHolder = (ProductHolder) item.getTag();
        if (position % 2 == 0)
            item.setBackgroundColor(item.getResources().getColor(R.color.colorAccent));
        else
            item.setBackgroundColor(item.getResources().getColor(R.color.colorPrimaryDark));
        //Setting data adapter to widgets
        productHolder. img.setImageResource(getItem(position).getImage());
        productHolder.tvName.setText(getItem(position).getName());
        productHolder.tvStock.setText("Stock: " + getItem(position).getFormatedUnitsInStock());
        productHolder.tvPrice.setText("Precio: " + getItem(position).getFormatedPrice());
        return item;
    }
    /**
     * Internal classh that contains XML file widgets
     */
    class ProductHolder{
        TextView tvName;
        TextView tvStock;
        TextView tvPrice;
        ImageView img;
    }
}
