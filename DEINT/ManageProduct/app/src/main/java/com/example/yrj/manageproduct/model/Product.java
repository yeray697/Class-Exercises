package com.example.yrj.manageproduct.model;

import android.os.StrictMode;

import java.util.Locale;
import java.util.UUID;

/**
 * Class with products
 * @author Yeray Ruiz
 */

public class Product {
    private String mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private String mDosage;
    private double mPrice;
    private int mStock;
    private int mImage;

    public Product(String mName, String mDescription, String mBrand, String mDosage, double mPrice, int mStock, int mImage){
        this.mId = UUID.randomUUID().toString();
        this.mName = mName;
        this.mDescription = mDescription;
        this.mBrand = mBrand;
        this.mDosage = mDosage;
        this.mPrice = mPrice;
        this.mStock = mStock;
        this.mImage = mImage;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getDosage() {
        return mDosage;
    }

    public void setDosage(String mDosage) {
        this.mDosage = mDosage;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getStock() {
        return mStock;
    }

    public void setStock(int mStock) {
        this.mStock = mStock;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public String getFormatedPrice() {
        return String.format(Locale.getDefault(),"$%s",mPrice);
    }

    public String getFormatedUnitsInStock(){
        return String.format(Locale.getDefault(), "%d u.",mStock);
    }

    @Override
    public String toString() {
        return mName +" " + mDosage;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) result = true;
        else {
            if (o != null && getClass() == o.getClass()) {
                Product product = (Product) o;
                if (mName.equals(product.mName))
                    if (mBrand.equals(product.mBrand))
                        result = mDosage.equals(product.mDosage);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mBrand != null ? mBrand.hashCode() : 0);
        result = 31 * result + (mDosage != null ? mDosage.hashCode() : 0);
        return result;
    }
}
