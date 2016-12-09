package com.example.yrj.manageproductrecycler.model;

import com.example.yrj.manageproductrecycler.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductRepository implements IRepository {

    private ArrayList<Product> products = new ArrayList<Product>();
    private static ProductRepository repository = new ProductRepository();

    public static ProductRepository getInstance() {
        return repository;
    }

    private ProductRepository(){

    }

    public List<Product> getAllProducts(){
        return products;
    }

    @Override
    public Product getProductBy(int id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }
}
