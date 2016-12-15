package com.example.yrj.manageproductrecycler.presenter;

import com.example.yrj.manageproductrecycler.interfaces.IManageProductPresenter;
import com.example.yrj.manageproductrecycler.interfaces.IManageProductView;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.ProductRepository;

/**
 * Created by usuario on 15/12/16.
 */

public class ManageProductPresenter implements IManageProductPresenter {
    IManageProductView view;
    public ManageProductPresenter(IManageProductView view){
        this.view = view;
    }
    @Override
    public void addProduct(Product product) {
        ProductRepository.getInstance().addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        ProductRepository.getInstance().updateProduct(product);
    }

    @Override
    public void onDestroy() {

    }
}
