package com.example.yrj.manageproductrecycler.presenter;

import com.example.yrj.manageproductrecycler.interfaces.IProductPresenter;
import com.example.yrj.manageproductrecycler.interfaces.IProductView;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.ProductRepository;

/**
 * Created by usuario on 20/10/16.
 */

public class ProductPresenter implements IProductPresenter {
    IProductView view;
    private ProductRepository repository;

    public ProductPresenter(IProductView view){
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
    public void addProduct(Product product) {
        repository.addProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
        view.showMessageDelete(product);
        //DEPENDE DE LA IMPLEMENTACIÓN
        loadProducts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
