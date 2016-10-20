package com.example.yrj.manageproduct;


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

    interface View{
        void setMessageError(String messageError, int idView);
    }
    interface Presenter{
        void validateCredentials(String name, String description, String brand, String dosage, String price, String stock);
    }
}