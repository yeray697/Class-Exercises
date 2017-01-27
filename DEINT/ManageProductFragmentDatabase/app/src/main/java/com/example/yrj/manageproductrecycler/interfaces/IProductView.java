package com.example.yrj.manageproductrecycler.interfaces;

import android.content.Context;

import com.example.yrj.manageproductrecycler.model.Product;

import java.util.List;

/**
 * Created by usuario on 15/12/16.
 */

public interface IProductView {
    public void showProducts(List<Product> products);
    public void showEmptyState(boolean show);
    public void showMessageDelete(Product product);

    Context getContext();
}
