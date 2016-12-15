package com.example.yrj.manageproductrecycler;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yrj.manageproductrecycler.interfaces.IManageProductPresenter;
import com.example.yrj.manageproductrecycler.interfaces.IManageProductView;
import com.example.yrj.manageproductrecycler.interfaces.IProduct;
import com.example.yrj.manageproductrecycler.interfaces.ManageProductListener;
import com.example.yrj.manageproductrecycler.model.Product;

public class ManageProduct_Fragment extends Fragment implements IManageProductView {
    Product product;
    EditText etName, etDescription, etBrand, etDosage, etPrice, etStock;
    Button btSave;
    ManageProductListener mCallback;

    public static ManageProduct_Fragment newInstance(Bundle args) {
        ManageProduct_Fragment fragment = new ManageProduct_Fragment();
        if (args != null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) //Modo edición
            product = (Product) getArguments().getParcelable(IProduct.PRODUCT_KEY);
        else {//Modo nuevo elemento
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ManageProductListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage() + " activity must implement ManageProductListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manage_product, container, false);

        etName = (EditText) view.findViewById(R.id.etName);
        etDescription = (EditText) view.findViewById(R.id.etDescription);
        etBrand = (EditText) view.findViewById(R.id.etBrand);
        etDosage = (EditText) view.findViewById(R.id.etDosage);
        etPrice = (EditText) view.findViewById(R.id.etPrice);
        etStock = (EditText) view.findViewById(R.id.etStock);
        btSave = (Button) view.findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        if (product != null) {
            etName.setText(product.getName());
            etDescription.setText(product.getDescription());
            etBrand.setText(product.getBrand());
            etDosage.setText(product.getDosage());
            etPrice.setText(String.valueOf(product.getPrice()));
            etStock.setText(String.valueOf(product.getStock()));
        }
        return view;
    }

    private void saveProduct() {
        if (product == null)
            product = new Product();
        product.setName(etName.getText().toString());
        product.setDescription(etDescription.getText().toString());
        product.setBrand(etBrand.getText().toString());
        product.setDosage(etDosage.getText().toString());
        product.setPrice(Double.parseDouble(etPrice.getText().toString()));
        product.setStock(Integer.parseInt(etStock.getText().toString()));

        mCallback.onListProductListener();
    }

    @Override
    public void showMessage(String message) {

    }
}
