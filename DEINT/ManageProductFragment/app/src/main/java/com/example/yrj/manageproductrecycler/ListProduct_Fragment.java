package com.example.yrj.manageproductrecycler;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yrj.manageproductrecycler.adapter.ProductAdapter;
import com.example.yrj.manageproductrecycler.interfaces.IProduct;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.presenter.ProductPresenter;

import java.util.List;

public class ListProduct_Fragment extends Fragment {

    public static final  String PRODUCT_KEY = "product";
    private ProductAdapter adapter;
    private ListView listProduct;
    private TextView emptyProduct;
    private boolean click = false;
    private ListProductListener mCallback;
    private static ProductPresenter presenter;

    interface ListProductListener {
        public void onManageProductListener(Bundle bundle);
    }

    public static ListProduct_Fragment newInstance(Bundle arguments) {
        ListProduct_Fragment fragment = new ListProduct_Fragment();
        if (arguments != null)
            fragment.setArguments(arguments);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListProductListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage() + " activity must implement ListProductListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void showProducts(List<Product> products){

    }

    private void hideList(boolean hide){

    }

    public void showEmptyState(){

    }

    public void showMessage(String message){

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.activity_product);
        adapter = new ProductAdapter(this);
        lvProduct = (ListView) findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);
        registerForContextMenu( lvProduct );
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProduct_Fragment.this, AddToList_Activity.class);
                startActivityForResult(intent,ADDPRODUCT_REQUESTCODE);
            }
        });
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(IProduct.PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                Intent intent = new Intent(ListProduct_Fragment.this, ManageProduct_Fragment.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,EDITPRODUCT_REQUESTCODE);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_sort_alphabetically:
                sortAdapterAlphabetically();
                break;
            case R.id.action_settings_general:
                intent = new Intent(ListProduct_Fragment.this, GeneralSetting_Activity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(ListProduct_Fragment.this, AccountSetting_Activity.class);
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
                Product product = (Product)data.getParcelableExtra(IProduct.PRODUCT_KEY);
                ((ProductAdapter)lvProduct.getAdapter()).addProduct(product);
            }
        }
        if (requestCode == EDITPRODUCT_REQUESTCODE) {
            if (resultCode == RESULT_OK){
                Product product = (Product)data.getParcelableExtra(IProduct.PRODUCT_KEY);
                ((ProductAdapter)lvProduct.getAdapter()).editProduct(product);

            }
        }
    }*/

    /**
     * Method that inflate the activity menu
     * @param menu
     * @return
     */
    /*@Override
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
    }*/
}
