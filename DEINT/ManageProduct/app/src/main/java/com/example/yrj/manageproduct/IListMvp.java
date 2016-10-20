package com.example.yrj.manageproduct;


import android.net.Uri;

/**
 * @author Yeray Ruiz
 */

public interface IListMvp {
    int CORRECT = 0;
    int NAME_EMPTY = 1;
    int DESCRIPTION_EMPTY = 2;
    int BRAND_EMPTY = 3;
    int DOSAGE_EMPTY = 4;
    int PRICE_EMPTY = 5;
    int STOCK_EMPTY = 6;
    int PHOTO_EMPTY = 7;

    interface View{
        void setMessageError(String messageError, int idView);
    }
    interface Presenter{
        boolean validateCredentials(String name, String description, String brand, String dosage, String price, String stock, Uri image);
    }
}