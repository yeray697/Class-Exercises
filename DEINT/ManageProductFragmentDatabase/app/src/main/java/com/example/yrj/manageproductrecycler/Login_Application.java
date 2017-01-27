package com.example.yrj.manageproductrecycler;

import android.app.Application;
import android.content.Context;

import com.example.yrj.manageproductrecycler.database.DatabaseHelper;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeray Ruiz
 */

public class Login_Application extends Application {
    private static Context context;
    private User user;

    public static Context getContext() {
        return context;
    }
    //ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
        DatabaseHelper.getInstance().open();
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        if (this.user != user)
            this.user = user;
    }

    /*public List<Product> getProducts(){
        return products;
    }

    public boolean addProduct(Product product){
        boolean result = false;
        if(!products.contains(product)) {
            products.add(product);
            result = true;
        }
        return result;
    }*/
}
