package com.example.yrj.manageproduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yrj.manageproduct.model.Product;

import java.util.List;

/**
 * Created by usuario on 20/10/16.
 */
public class CustomAdapter extends BaseAdapter {
    private String[] name;
    private Context context;
    private int[] stock, image;
    private static LayoutInflater inflater=null;

    public CustomAdapter(ListProduct_Activity listProduct_activity, List<Product> products) {
        super();
        this.context = listProduct_activity;
        this.name = new String[products.size()];
        this.stock = new int[products.size()];
        this.image = new int[products.size()];
        int contador = 0;
        for (Product product:products) {
            this.name[contador] = product.getName();
            this.stock[contador] = product.getStock();
            this.image[contador] = product.getImage();
            contador++;
        }
        //this.name = productsName;
        //this.stock = productsStock;
        //this.image = productsImage;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tvName;
        TextView tvStock;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.listrow,null);
        holder.tvName= (TextView) rowView.findViewById(R.id.tvNameRow);
        holder.tvStock= (TextView) rowView.findViewById(R.id.tvStockRow);
        holder.tvName.setText(name[position]);
        holder.tvStock.setText(stock[position]);
        holder.img.setImageResource(image[position]);
        holder.img= (ImageView) rowView.findViewById(R.id.imgRow);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+name[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
