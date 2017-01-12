package com.example.yrj.manageproductrecycler.interfaces;

/**
 * Created by usuario on 15/12/16.
 */

public interface ILoginPresenter {
    int MINLENGTH = 8;
    int validateUser(String user);
    int validatePass(String pass);
}
