package com.example.yrj.manageproductrecycler.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.yrj.manageproductrecycler.database.DatabaseManager;
import com.example.yrj.manageproductrecycler.interfaces.IProductPresenter;
import com.example.yrj.manageproductrecycler.interfaces.IProductView;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.ProductRepository;

import java.util.List;

/**
 * Created by usuario on 20/10/16.
 */

public class ProductPresenter implements IProductPresenter {
    IProductView view;

    public ProductPresenter(IProductView view){
        this.view = view;
    }

    @Override
    public void loadProducts() {
        new AsyncTask<Void,List<Product>,List<Product>>(){
            ProgressDialog progressDialog = new ProgressDialog(view.getContext());
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Loading . . .");
                progressDialog.show();
            }

            @Override
            protected List<Product> doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return DatabaseManager.getInstance().getAllProducts();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                if (progressDialog != null)
                    progressDialog.dismiss();
                view.showEmptyState(true);
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                super.onPostExecute(products);
                if (progressDialog != null)
                    progressDialog.dismiss();
                view.showProducts(products);
            }
        }.execute();
        //if (DatabaseManager.getInstance().getAllProducts().isEmpty()){
        //    view.showEmptyState(true);
        //} else {
        //    view.showProducts(DatabaseManager.getInstance().getAllProducts());
        //}
    }

    @Override
    public Product getProduct(int id) {
        return DatabaseManager.getInstance().getProductBy(id);
    }

    @Override
    public void addProduct(Product product) {
        DatabaseManager.getInstance().addProduct(product);
        loadProducts();
    }

    @Override
    public void deleteProduct(Product product) {
        DatabaseManager.getInstance().deleteProduct(product);
        view.showMessageDelete(product);
        //DEPENDE DE LA IMPLEMENTACIÓN
        loadProducts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
