package com.example.yrj.manageproduct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Double2;
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
    public boolean validateCredentials(String name, String description, String brand, String dosage, String price, String stock, Uri image) {
        String error = "";
        boolean result = false;
        int idView = -1;
        int isThereAnError = IListMvp.CORRECT;
        //Checking conditions
        if (TextUtils.isEmpty(name)) {
            isThereAnError = IListMvp.NAME_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_name);
            idView = R.id.etName;
        }
        else if (TextUtils.isEmpty(description)) {
            isThereAnError = IListMvp.DESCRIPTION_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_description);
            idView = R.id.etDescription;
        }
        else if (TextUtils.isEmpty(brand)) {
            isThereAnError = IListMvp.BRAND_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_brand);
            idView = R.id.etBrand;
        }
        else if (TextUtils.isEmpty(dosage)) {
            isThereAnError = IListMvp.DOSAGE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_dosage);
            idView = R.id.etDosage;
        }
        else if (TextUtils.isEmpty(price)) {
            isThereAnError = IListMvp.PRICE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_price);
            idView = R.id.etPrice;
        }
        else if (TextUtils.isEmpty(stock)) {
            isThereAnError = IListMvp.STOCK_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_stock);
            idView = R.id.etStock;
        }
        else if (image == null) {
            isThereAnError = IListMvp.PHOTO_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_photo);
            idView = -2;
        }
        else { //If there is no error
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name",name);
            returnIntent.putExtra("description",description);
            returnIntent.putExtra("brand",brand);
            returnIntent.putExtra("dosage", dosage);
            returnIntent.putExtra("price", Double.parseDouble(price));
            returnIntent.putExtra("stock",Integer.parseInt(stock));
            returnIntent.putExtra("image",image);
            ((Activity)view).setResult(Activity.RESULT_OK,returnIntent);
            ((Activity)view).finish();
            result = true;
        }
        //If there is an error, we set it on the view
        if (isThereAnError != ILoginMvp.CORRECT) {
            view.setMessageError(error, idView);
        }
        return result;
    }
}
