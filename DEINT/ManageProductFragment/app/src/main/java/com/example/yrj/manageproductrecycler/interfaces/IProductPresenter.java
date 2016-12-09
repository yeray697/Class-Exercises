package com.example.yrj.manageproductrecycler.interfaces;

import com.example.yrj.manageproductrecycler.model.Product;

import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public interface IProductPresenter {

    interface View{
        public void showProducts(List<Product> products);
        public void showEmptyState(boolean show);
        public void showMessage(String message);
    }
    public void loadProducts();
    public Product getProduct(int id);
    public void deleteProdduct(Product product);
    public void onDestroy();
}
