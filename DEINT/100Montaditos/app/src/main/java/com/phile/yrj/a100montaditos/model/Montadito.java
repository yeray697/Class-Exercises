package com.phile.yrj.a100montaditos.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.phile.yrj.a100montaditos.MontaditosPreferences;

/**
 * Created by usuario on 12/12/16.
 */

public class Montadito implements Parcelable{

    private static final int MAX_AMOUNT = 10;
    private int number;
    private String name;
    private double price;
    private int amount;

    public Montadito(int number, String name, double price) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.amount = 0;
    }

    protected Montadito(Parcel in) {
        number = in.readInt();
        name = in.readString();
        price = in.readDouble();
        amount = in.readInt();
    }

    public static final Creator<Montadito> CREATOR = new Creator<Montadito>() {
        @Override
        public Montadito createFromParcel(Parcel in) {
            return new Montadito(in);
        }

        @Override
        public Montadito[] newArray(int size) {
            return new Montadito[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPriceFormmated() {
        return price + " €";
    }

    public int getNumber() {
        return number;
    }

    public int getAmount(){
        return this.amount;
    }
    public String getAmountParsed(){
        return "x"+this.amount;
    }
    public void increaseAmount() throws Exception {
        if (amount < MAX_AMOUNT)
            changeAmount(+1);
        else
            throw new Exception("max_montaditos");
    }
    public void decreaseAmount(){
        if (amount > 0)
            changeAmount(-1);

    }
    private void changeAmount(int n) {
        this.amount += n;
        MontaditosPreferences.getInstance().setAmount(this.number, this.amount);
    }

    @Override
    public String toString() {
        String text;
        if (number < 1)
            text = name;
        else
            text = number + ". " + name;

        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeInt(amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
