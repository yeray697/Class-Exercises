package com.example.yrj.manageproductrecycler.interfaces;

import com.example.yrj.manageproductrecycler.model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public interface IRepository {
    public Product getProductBy(int id);
    public void deleteProduct(Product product);
    public void updateProduct(Product product);
}
