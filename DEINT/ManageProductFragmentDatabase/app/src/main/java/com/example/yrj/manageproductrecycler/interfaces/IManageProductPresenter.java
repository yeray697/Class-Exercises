package com.example.yrj.manageproductrecycler.interfaces;

import com.example.yrj.manageproductrecycler.model.Product;

/**
 * Created by usuario on 15/12/16.
 */

public interface IManageProductPresenter {
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void onDestroy();
}
