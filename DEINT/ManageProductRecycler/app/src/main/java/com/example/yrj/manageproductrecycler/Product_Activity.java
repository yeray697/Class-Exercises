package com.example.yrj.manageproductrecycler;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.adapter.ProductAdapterRecylcer;
import com.example.yrj.manageproductrecycler.model.Product;

public class Product_Activity extends AppCompatActivity{

    private boolean order = false;
    private static final int ADDPRODUCT_REQUESTCODE = 0;
    private static final int EDITPRODUCT_REQUESTCODE = 1;
    private ProductAdapterRecylcer adapter;
    private RecyclerView rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        adapter = new ProductAdapterRecylcer(this);
        rvProduct = (RecyclerView) findViewById(R.id.rvProduct);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        rvProduct.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_add_product:
                intent = new Intent(Product_Activity.this, AddToList_Activity.class);
                startActivityForResult(intent,ADDPRODUCT_REQUESTCODE);
                break;
            case R.id.action_sort_alphabetically:
                sortAdapterAlphabetically();
                break;
            case R.id.action_settings_general:
                intent = new Intent(Product_Activity.this, GeneralSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(Product_Activity.this, AccountSettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortAdapterAlphabetically() {
        adapter.sortProducts();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If we edited or added a product, we update the list
        if (requestCode == ADDPRODUCT_REQUESTCODE || requestCode == EDITPRODUCT_REQUESTCODE){
            if (resultCode == RESULT_OK){

            }
        }
    }

    /**
     * Method that inflate the activity menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
