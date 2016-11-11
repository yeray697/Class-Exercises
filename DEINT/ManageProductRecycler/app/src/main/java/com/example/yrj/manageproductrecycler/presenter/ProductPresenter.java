package com.example.yrj.manageproductrecycler.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.example.yrj.manageproductrecycler.interfaces.IProductMvp;
import com.example.yrj.manageproductrecycler.R;

/**
 * Created by usuario on 20/10/16.
 */

public class ProductPresenter implements IProductMvp.Presenter {
    IProductMvp.View view;
    public ProductPresenter(IProductMvp.View view){
        this.view = view;
    }

    @Override
    public boolean validateCredentials(String name, String description, String brand, String dosage, String price, String stock, Uri image) {
        String error = "";
        boolean result = false;
        int idView = -1;
        int isThereAnError = IProductMvp.CORRECT;
        //Checking conditions
        if (TextUtils.isEmpty(name)) {
            isThereAnError = IProductMvp.NAME_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_name);
            idView = R.id.etName;
        }
        else if (TextUtils.isEmpty(description)) {
            isThereAnError = IProductMvp.DESCRIPTION_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_description);
            idView = R.id.etDescription;
        }
        else if (TextUtils.isEmpty(brand)) {
            isThereAnError = IProductMvp.BRAND_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_brand);
            idView = R.id.etBrand;
        }
        else if (TextUtils.isEmpty(dosage)) {
            isThereAnError = IProductMvp.DOSAGE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_dosage);
            idView = R.id.etDosage;
        }
        else if (TextUtils.isEmpty(price)) {
            isThereAnError = IProductMvp.PRICE_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_price);
            idView = R.id.etPrice;
        }
        else if (TextUtils.isEmpty(stock)) {
            isThereAnError = IProductMvp.STOCK_EMPTY;
            error = ((Context)view).getResources().getString(R.string.data_empty_stock);
            idView = R.id.etStock;
        }
        else if (image == null) {
            isThereAnError = IProductMvp.PHOTO_EMPTY;
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
