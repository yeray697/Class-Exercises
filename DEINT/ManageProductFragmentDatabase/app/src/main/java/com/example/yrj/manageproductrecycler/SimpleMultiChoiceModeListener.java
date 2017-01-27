package com.example.yrj.manageproductrecycler;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import java.util.ArrayList;
/**
 * Created by usuario on 16/12/16.
 */

public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

    Context context;
    int statusbarColor;
    SparseBooleanArray checkedList;
    ArrayList<View> listView;
    int cont;

    public SimpleMultiChoiceModeListener(Context context, ArrayList<View> listView){
        this.context = context;
        checkedList = new SparseBooleanArray();
        cont = 0;
        this.listView = listView;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            cont++;
            checkedList.append(position,checked);
        } else {
            cont--;
            checkedList.delete(position);
        }
        mode.setTitle(String.valueOf(cont));
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflter = mode.getMenuInflater();
        inflter.inflate(R.menu.menu_contextual, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusbarColor = ((AppCompatActivity) context).getWindow().getStatusBarColor();
            ((AppCompatActivity) context).getWindow().setStatusBarColor(ContextCompat.getColor(context,R.color.colorBackgroundStatusbar));
        }
        for (View view:listView){
            view.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteProducts();
                break;
        }
        mode.finish();
        return true;
    }

    private void deleteProducts() {

        cont = 0;
        checkedList.clear();

    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity) context).getWindow().setStatusBarColor(statusbarColor);
        }
        for (View view:listView){
            view.setVisibility(View.VISIBLE);
        }
    }
}
