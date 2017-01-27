package com.example.yrj.manageproductrecycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.Login_Application;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.database.DatabaseManager;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 24/10/16.
 */

public class ProductAdapterRecylcer extends RecyclerView.Adapter<ProductAdapterRecylcer.ProductViewHolder>{

    private List<Product> products;
    private Context context;
    Boolean order = false;

    public ProductAdapterRecylcer (Context context){
        this.context = context;
        this.products = new ArrayList<Product>();
        this.products =
                DatabaseManager.getInstance().getAllProducts();
        sortProducts();
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

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), tvName.getText()+": on click", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), tvName.getText()+": on long click", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void sortProducts(){
        order = !order;
        if (order)
            Collections.sort(products, Product.NAME_ASC_COMPARATOR);
        else
            Collections.sort(products, Product.NAME_DESC_COMPARATOR);
        notifyDataSetChanged();
    }

}
