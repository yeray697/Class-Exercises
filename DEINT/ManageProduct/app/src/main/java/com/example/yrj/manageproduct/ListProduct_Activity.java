package com.example.yrj.manageproduct;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yrj.manageproduct.model.CustomAdapter;

public class ListProduct_Activity extends ListActivity{

    private static final int ADDTOLISTREQUEST = 1;
    //private ArrayAdapter<Product> adapter;
    private Button btAddToList;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        //adapter = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1, ((LoginApplication) getApplication()).getProducts());
        //this.getListView().setAdapter(adapter);

        this.getListView().setAdapter(new CustomAdapter(this,((LoginApplication) getApplication()).getProducts()));

        btAddToList = (Button) findViewById(R.id.btAddToList);
        btAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProduct_Activity.this, AddToList_Activity.class);
                startActivityForResult(intent,ADDTOLISTREQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADDTOLISTREQUEST){
            if (resultCode == RESULT_OK){
                String name, description, brand, dosage;
                double price, stock;
                name = data.getExtras().getString("name");
                description = data.getExtras().getString("description");
                brand = data.getExtras().getString("brand");
                dosage = data.getExtras().getString("dosage");
                price = data.getExtras().getDouble("price");
                stock = data.getExtras().getDouble("stock");
                //get image?????
                //Product product = new Product(name, description, brand, dosage, price,  stock, image);
            }
            else
                Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }
}
