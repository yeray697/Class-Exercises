package com.example.yrj.manageproductrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.yrj.manageproductrecycler.interfaces.IProduct;

import java.util.Comparator;
import java.util.Locale;
import java.util.UUID;

/**
 * Class with products
 * @author Yeray Ruiz
 */

public class Product implements Comparable<Product>, IProduct, Parcelable{
    private String mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private String mDosage;
    private double mPrice;
    private int mStock;
    private int mImage;

    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getPrice(),p2.getPrice());
        }
    };
    public static final Comparator<Product> NAME_ASC_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };
    public static final Comparator<Product> NAME_DESC_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return -1 * p1.getName().compareTo(p2.getName());
        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getStock(),p2.getStock());
        }
    };

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
    public Product(){

    }

    protected Product(Parcel in) {
        mId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mBrand = in.readString();
        mDosage = in.readString();
        mPrice = in.readDouble();
        mStock = in.readInt();
        mImage = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return mId;
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
                if (mName.equalsIgnoreCase(product.mName))
                    if (mBrand.equalsIgnoreCase(product.mBrand))
                        result = mDosage.equalsIgnoreCase(product.mDosage);
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

    @Override
    public int compareTo(Product p) {
        //TODO
        int result = this.getName().compareTo(p.getName());
        if (result == 0) {
            result = this.getBrand().compareTo(p.getBrand());
            if (result == 0) {
                result = this.getDosage().compareTo(p.getDosage());
            }
        }


        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mBrand);
        dest.writeString(mDosage);
        dest.writeDouble(mPrice);
        dest.writeInt(mStock);
        dest.writeInt(mImage);
    }
}
