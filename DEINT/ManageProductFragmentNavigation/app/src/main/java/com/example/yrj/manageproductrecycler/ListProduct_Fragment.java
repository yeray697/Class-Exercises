package com.example.yrj.manageproductrecycler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
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
import com.example.yrj.manageproductrecycler.interfaces.IProductView;
import com.example.yrj.manageproductrecycler.interfaces.ListProductListener;
import com.example.yrj.manageproductrecycler.model.Product;
import com.example.yrj.manageproductrecycler.presenter.ProductPresenter;

import java.util.List;

public class ListProduct_Fragment extends Fragment implements IProductView {

    private ProductAdapter adapter;
    private FloatingActionButton fabAdd;
    private ListProductListener mCallback;
    private ProductPresenter presenter;
    private ListView listProduct;
    private TextView tvEmpty;
    private boolean click = false;

    public static ListProduct_Fragment newInstance(Bundle arguments) {
        ListProduct_Fragment fragment = new ListProduct_Fragment();
        if (arguments != null)
            fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductAdapter(getContext());
        presenter = new ProductPresenter(this);
        /*
            Esta opción le dice a la Activity que el fragment tiene su propio menú y llama
            al método callback onCreateOptionsMenu()
         */
        setHasOptionsMenu(true);
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
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_product,container, false);
        tvEmpty = (TextView)rootView.findViewById(android.R.id.empty);
        listProduct = (ListView) rootView.findViewById(android.R.id.list);
        listProduct.setAdapter(adapter);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(IProduct.PRODUCT_KEY, (Product) parent.getItemAtPosition(position));
                mCallback.showManageProduct(bundle);
            }
        });
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.showManageProduct(null);
            }
        });
        // ¡¡NO ES NECESARIO IMPLEMENTAR EL EVENTO ONLONGCLICKLISTENER!!
        registerForContextMenu(listProduct);

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final int position = info.position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false)
                        .setTitle("Cuidado")
                        .setMessage("¿Seguro que quieres borrarlo?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteProduct((Product) listProduct.getItemAtPosition(position));
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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        ((Activity)getContext()).getMenuInflater().inflate(R.menu.menu_listproduct, menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort_alphabetically:
                adapter.sortProducts();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProducts(List<Product> products){
        adapter.updateProducts(products);
    }


    private void hideList(boolean hide){
        if (hide) {
            tvEmpty.setVisibility(View.VISIBLE);
            listProduct.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            listProduct.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showEmptyState(boolean show){
        hideList(show);
    }

    public void showMessage(String message){
    }

    @Override
    public void showMessageDelete(final Product product){
        Snackbar.make(getView(),"Producto eliminado", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addProduct(product);
                        presenter.loadProducts();
                    }
                }).show();

        /*
        //SETCALLBACK (Hacer una llamada a un método callback de un snackbar
        // incluso si el SnackBar se ha eliminado mediante Swipe
        .setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_SWIPE) {
                    presenter.deleteProduct(product);
                }
            }
        }).show();*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        presenter = null;
    }
}
