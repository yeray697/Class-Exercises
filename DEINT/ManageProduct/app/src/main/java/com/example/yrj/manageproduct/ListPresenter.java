package com.example.yrj.manageproduct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.yrj.manageproduct.model.Product;

/**
 * Created by usuario on 20/10/16.
 */

public class ListPresenter implements IListMvp.Presenter{
    IListMvp.View view;
    public ListPresenter(IListMvp.View view){
        this.view = view;
    }

    @Override
    public void validateCredentials(String name, String description, String brand, String dosage, String price, String stock) {
        String error = "";
        int idView = -1;
        int result = IListMvp.CORRECT;
        //Checking conditions
        if (TextUtils.isEmpty(name)) {
            result = IListMvp.NAME_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_user);
            idView = R.id.etName;
        }
        else if (TextUtils.isEmpty(description)) {
            result = IListMvp.DESCRIPTION_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.etDescription;
        }
        else if (TextUtils.isEmpty(brand)) {
            result = IListMvp.BRAND_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.etBrand;
        }
        else if (TextUtils.isEmpty(dosage)) {
            result = IListMvp.DOSAGE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.etDosage;
        }
        else if (TextUtils.isEmpty(price)) {
            result = IListMvp.PRICE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.etPrice;
        }
        else if (TextUtils.isEmpty(stock)) {
            result = IListMvp.STOCK_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_pass);
            idView = R.id.etStock;
        }
        else { //If there is no error
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name",name);
            returnIntent.putExtra("description",description);
            returnIntent.putExtra("brand",brand);
            returnIntent.putExtra("dosage", dosage);
            returnIntent.putExtra("price", price);
            returnIntent.putExtra("stock",stock);
            //returnIntent.putExtra("image",image);
            ((Activity)view).setResult(Activity.RESULT_OK,returnIntent);
            ((Activity)view).finish();
        }
        //If there is an error, we set it on the view
        if (result != ILoginMvp.CORRECT) {
            view.setMessageError(error, idView);
        }
    }
}
