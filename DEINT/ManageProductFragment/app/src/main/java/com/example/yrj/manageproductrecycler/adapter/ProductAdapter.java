package com.example.yrj.manageproductrecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yrj.manageproductrecycler.Login_Application;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 18/11/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    boolean asc;

    /**
     * We pass as third parameter in super an ArrayList with elements from repository.
     * We have a local copy different than the repository copy
     * @param context
     */
    public ProductAdapter(Context context) {
        super(context,
                R.layout.listrow,
                ((Login_Application)context.getApplicationContext()).getProducts());
        asc = true;
    }

    public ProductAdapter(Context context, List<Product> list) {
        super(context,
                R.layout.listrow,
                new ArrayList<Product>(list));
        asc = true;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ProductHolder holder;
        if (view == null) {
            //LayoutInflater inflater = LayoutInflater.from(getContext());
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listrow,null);
            holder = new ProductHolder();
            holder.img = (ImageView) view.findViewById(R.id.imgRow);
            holder.tvName = (TextView) view.findViewById(R.id.tvNameRow);
            holder.tvPrice = (TextView) view.findViewById(R.id.tvPriceRow);
            holder.tvStock = (TextView) view.findViewById(R.id.tvStockRow);
            view.setTag(holder);
        } else {
            holder = (ProductHolder) view.getTag();
        }
        holder.img.setImageResource(getItem(position).getImage());
        holder.tvName.setText(getItem(position).getName());
        holder.tvPrice.setText(getItem(position).getFormatedPrice());
        holder.tvStock.setText(getItem(position).getFormatedUnitsInStock());
        return view;
    }

    public void addProduct(Product product) {
        add(product);
        notifyDataSetChanged();
    }

    public void removeProduct(int position) {
        remove(getItem(position));
        notifyDataSetChanged();
    }

    public void editProduct(Product product) {
        Product productAux;
        for (int i = 0; i<getCount(); i++) {
            productAux = getItem(i);
            if (productAux.getId().equals(product.getId())) {
                remove(productAux);
                insert(product,i);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public List<Product> getList(){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i<getCount(); i++) {
            products.add(getItem(i));
        }
        return products;
    }
    class ProductHolder{
        ImageView img;
        TextView tvName;
        TextView tvPrice;
        TextView tvStock;

    }

    public void sortProducts() {
        if (asc) {
            sort(Product.NAME_ASC_COMPARATOR);
        } else {
            sort(Collections.reverseOrder());
            //sort(Product.NAME_DESC_COMPARATOR);
        }
        notifyDataSetChanged();
        asc = !asc;
    }
}
