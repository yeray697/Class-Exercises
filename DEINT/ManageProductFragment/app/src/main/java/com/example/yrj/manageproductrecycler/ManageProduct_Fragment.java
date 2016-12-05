package com.example.yrj.manageproductrecycler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yrj.manageproductrecycler.interfaces.IProduct;
import com.example.yrj.manageproductrecycler.model.Product;

public class ManageProduct_Fragment extends AppCompatActivity {
    Product product;
    EditText etName, etDescription, etBrand, etDosage, etPrice, etStock;
    Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        product = (Product) getIntent().getExtras().getParcelable(IProduct.PRODUCT_KEY);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etDosage = (EditText) findViewById(R.id.etDosage);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etStock = (EditText) findViewById(R.id.etStock);
        btSave = (Button) findViewById(R.id.btSave);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        etName.setText(product.getName());
        etDescription.setText(product.getDescription());
        etBrand.setText(product.getBrand());
        etDosage.setText(product.getDosage());
        etPrice.setText(String.valueOf(product.getPrice()));
        etStock.setText(String.valueOf(product.getStock()));
    }

    private void saveProduct() {
        product.setName(etName.getText().toString());
        product.setDescription(etDescription.getText().toString());
        product.setBrand(etBrand.getText().toString());
        product.setDosage(etDosage.getText().toString());
        product.setPrice(Double.parseDouble(etPrice.getText().toString()));
        product.setStock(Integer.parseInt(etStock.getText().toString()));

        Intent intent = new Intent (ManageProduct_Fragment.this, ListProduct_Fragment.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(IProduct.PRODUCT_KEY, product);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
