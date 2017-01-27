package com.example.yrj.manageproductrecycler.interfaces;

import android.app.ProgressDialog;

import com.example.yrj.manageproductrecycler.model.Product;

/**
 * Created by usuario on 15/12/16.
 */

public interface IProductPresenter {
    public void loadProducts();
    public Product getProduct(int id);
    public void addProduct(Product product);
    public void deleteProduct(Product product);
    public void onDestroy();
}
