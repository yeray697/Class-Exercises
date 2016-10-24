package com.example.yrj.manageproductrecycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yrj.manageproductrecycler.LoginApplication;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.model.Product;
import java.util.List;

/**
 * Created by usuario on 24/10/16.
 */

public class ProductAdapterRecylcer extends RecyclerView.Adapter<ProductAdapterRecylcer.ProductViewHolder>{

    private List<Product> products;
    private Context context;

    public ProductAdapterRecylcer (Context context){
        this.context = context;
        this.products = ((LoginApplication) context.getApplicationContext()).getProducts();
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.img.setImageResource(products.get(position).getImage());
        holder.tvName.setText(products.get(position).getName());
        holder.tvStock.setText(products.get(position).getFormatedUnitsInStock());
        holder.tvPrice.setText(products.get(position).getFormatedPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tvName;
        TextView tvPrice;
        TextView tvStock;

        public ProductViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgRow);
            tvName = (TextView) itemView.findViewById(R.id.tvNameRow);
            tvStock = (TextView) itemView.findViewById(R.id.tvStockRow);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPriceRow);
        }
    }
}
