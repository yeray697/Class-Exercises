package com.example.yrj.manageproductrecycler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yrj.manageproductrecycler.adapter.ProductAdapter;
import com.example.yrj.manageproductrecycler.adapter.ProductAdapterRecylcer;
import com.example.yrj.manageproductrecycler.interfaces.IProduct;
import com.example.yrj.manageproductrecycler.model.Product;

public class Product_Activity extends AppCompatActivity{

    private boolean order = false;
    private static final int ADDPRODUCT_REQUESTCODE = 0;
    private static final int EDITPRODUCT_REQUESTCODE = 1;
    private ProductAdapter adapter;
    private ListView lvProduct;
    private FloatingActionButton fabAdd;
    //private RecyclerView rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        adapter = new ProductAdapter(this);
        lvProduct = (ListView) findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);
        registerForContextMenu( lvProduct );
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product_Activity.this, AddToList_Activity.class);
                startActivityForResult(intent,ADDPRODUCT_REQUESTCODE);
            }
        });
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IProduct.PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                Intent intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,EDITPRODUCT_REQUESTCODE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
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
        if (requestCode == ADDPRODUCT_REQUESTCODE ){
            if (resultCode == RESULT_OK){
                Product product = (Product)data.getExtras().getSerializable(IProduct.PRODUCT_KEY);
                ((ProductAdapter)lvProduct.getAdapter()).addProduct(product);
            }
        }
        if (requestCode == EDITPRODUCT_REQUESTCODE) {
            if (resultCode == RESULT_OK){
                Product product = (Product)data.getExtras().getSerializable(IProduct.PRODUCT_KEY);
                ((ProductAdapter)lvProduct.getAdapter()).editProduct(product);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        if (v.getId() == R.id.lvProduct) {
            menu.add(v.getId(), 1, 0, "Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final int position = info.position;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false)
                        .setTitle("Cuidado")
                        .setMessage("¿Seguro que quieres borrarlo?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.removeProduct(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
        }
        return true;
    }
}
