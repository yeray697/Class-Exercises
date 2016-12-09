package com.example.yrj.manageproductrecycler.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.yrj.manageproductrecycler.interfaces.IProduct;
import com.example.yrj.manageproductrecycler.interfaces.IProductPresenter;
import com.example.yrj.manageproductrecycler.interfaces.ListProductPresenter;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.ProductRepository;

/**
 * Created by usuario on 20/10/16.
 */

public class ProductPresenter implements IProductPresenter {
    IProductPresenter.View view;
    private ProductRepository repository;

    public ProductPresenter(IProductPresenter.View view){
        this.view = view;
        this.repository = ProductRepository.getInstance();
    }

    @Override
    public void loadProducts() {
        if (repository.getAllProducts().isEmpty()){
            view.showEmptyState(true);
        } else {
            view.showProducts(repository.getAllProducts());
        }
    }

    @Override
    public Product getProduct(int id) {
        return repository.getProductBy(id);
    }

    @Override
    public void deleteProdduct(Product product) {
        repository.deleteProduct(product);

        //DEPENDE DE LA IMPLEMENTACIÓN
        loadProducts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
