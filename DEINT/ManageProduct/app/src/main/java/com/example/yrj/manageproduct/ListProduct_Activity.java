package com.example.yrj.manageproduct;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.yrj.manageproduct.model.Product;

public class ListProduct_Activity extends ListActivity {

    private ArrayAdapter<Product> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        this.getListView().setAdapter(new CustomAdapter(this,((LoginApplication) getApplication()).getProducts()));
        //adapter = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1, ((LoginApplication) getApplication()).getProducts());
        //this.getListView().setAdapter(adapter);
    }
}
