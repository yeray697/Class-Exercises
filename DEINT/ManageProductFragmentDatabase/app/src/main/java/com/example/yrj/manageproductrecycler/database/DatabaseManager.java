package com.example.yrj.manageproductrecycler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.yrj.manageproductrecycler.Login_Application;
import com.example.yrj.manageproductrecycler.ManageProduct_Fragment;
import com.example.yrj.manageproductrecycler.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/01/17.
 */

public class DatabaseManager {
    private Context context;
    private static DatabaseManager databaseManager;

    private DatabaseManager(Context context){
        this.context = context;
    }
    public static DatabaseManager getInstance(){
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(Login_Application.getContext());
        }
        return databaseManager;
    }

    public void addProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, product.getIdCategory());

        SQLiteDatabase sqliteDatabase = DatabaseHelper.getInstance().openDatabase();

            sqliteDatabase.insertOrThrow(ManageProductContract.ProductEntry.TABLE_NAME,null,contentValues);

        DatabaseHelper.getInstance().closeDatabase();
    }

    public void deleteProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String where = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{BaseColumns._ID, String.valueOf(product.getId())};
        sqLiteDatabase.delete(ManageProductContract.ProductEntry.TABLE_NAME,where,whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public void updateProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, product.getIdCategory());
        String where = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{BaseColumns._ID, String.valueOf(product.getId())};

        SQLiteDatabase sqliteDatabase = DatabaseHelper.getInstance().openDatabase();
        sqliteDatabase.update(ManageProductContract.ProductEntry.TABLE_NAME,contentValues,where,whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public List<Product> getAllProducts() {
        Product product;
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query( ManageProductContract.ProductEntry.TABLE_NAME, ManageProductContract.ProductEntry.ALL_COLUMNS,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setBrand(cursor.getString(3));
                product.setDosage(cursor.getString(4));
                product.setPrice(cursor.getFloat(5));
                product.setStock(cursor.getInt(6));
                product.setImage(Integer.parseInt(cursor.getString(7)));
                product.setIdCategory(cursor.getInt(8));
            } while (cursor.moveToNext());
        }
        DatabaseHelper.getInstance().closeDatabase();
        return products;
    }

    public Cursor getAllCategory() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(ManageProductContract.CategoryEntry.TABLE_NAME, ManageProductContract.CategoryEntry.ALL_COLUMNS,null,null,null,null,null);
        DatabaseHelper.getInstance().closeDatabase();
        return cursor;
    }

    public Product getProductBy(int id) {

        return null;
    }
}
