package com.example.yrj.manageproductrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yrj.manageproductrecycler.adapter.ProductAdapterRecylcer;
import com.example.yrj.manageproductrecycler.model.Product;

public class Product_Activity extends AppCompatActivity{

    private ProductAdapterRecylcer adapter;
    private RecyclerView rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        adapter = new ProductAdapterRecylcer(this);
        rvProduct = (RecyclerView) findViewById(R.id.rvProduct);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        rvProduct.setAdapter(adapter);
    }
}
