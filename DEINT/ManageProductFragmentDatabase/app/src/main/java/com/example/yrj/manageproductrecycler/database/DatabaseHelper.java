package com.example.yrj.manageproductrecycler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.yrj.manageproductrecycler.Login_Application;
import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.model.Product;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "ManageProduct.db";

    private volatile static DatabaseHelper instance;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    public synchronized static DatabaseHelper getInstance() {
        if (instance == null){
            instance = new DatabaseHelper(Login_Application.getContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger(0);
    }

    public synchronized SQLiteDatabase openDatabase(){
        if (mOpenCounter.incrementAndGet() == 1){
            mDatabase = getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase(){
        if (mOpenCounter.decrementAndGet() == 0){
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.CategoryEntry.SQL_INSERT_ENTRIES);
            db.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            /*
             */
            /*DatabaseManager databaseInstance = DatabaseManager.getInstance();
            databaseInstance.addProduct(new Product(1,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","400 MG 30 SOBRES GRANULADO ",3.23,26, R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(2,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","600 MG 20 SOBRES GRANULADO",3.73,15,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(3,"Valium","Es una droga que se receta ampliamente contra la ansiedad, y de la que también se abusa mucho","ROCHE FARMA","10 MG 6 AMPOLLAS 2 ML",2.61,65,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(4,"Frenadol Complex","Es un medicamento indicado para el alivio sintomático de gripes y catarros","MCNEIL IBERIC","10 SOBRES",4.95,64,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(5,"Frenadol","Te ayudan a frenar los síntomas de la gripe y el resfriado","MCNEIL IBERIC","10 COMPRIMIDOS EFERVESCENTES",4.95,26, R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(6,"Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 10 COMPR MASTIC",3.67,48,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(7,"Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 20 COMPRIMIDOS",3.36,48,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(8,"Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","200MG 20 sobres",7.77,10,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(9,"Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","100mg/5ml susp 200ml",3.48,19,R.mipmap.ic_launcher,1));
            databaseInstance.addProduct(new Product(10,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas\"","CINFA","600 MG 40 SOBRES GRANULADO ",5.92,50,R.mipmap.ic_launcher,1));
            */
            /*db.execSQL(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY +
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","400 MG 30 SOBRES GRANULADO",3.23,26, R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","600 MG 20 SOBRES GRANULADO",3.73,15,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas\"","CINFA","600 MG 40 SOBRES GRANULADO ",5.92,50,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","100mg/5ml susp 200ml",3.48,19,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","200MG 20 sobres",7.77,10,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 20 COMPRIMIDOS",3.36,48,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 10 COMPR MASTIC",3.67,48,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Frenadol","Te ayudan a frenar los síntomas de la gripe y el resfriado","MCNEIL IBERIC","10 COMPRIMIDOS EFERVESCENTES",4.95,26, R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Frenadol Complex","Es un medicamento indicado para el alivio sintomático de gripes y catarros","MCNEIL IBERIC","10 SOBRES",4.95,64,R.mipmap.ic_launcher,"1")+","+
                    String.format(ManageProductContract.ProductEntry.SQL_INSERT_ENTRY_VALUES,"Valium","Es una droga que se receta ampliamente contra la ansiedad, y de la que también se abusa mucho","ROCHE FARMA","10 MG 6 AMPOLLAS 2 ML",2.61,65,R.mipmap.ic_launcher,"1")+";");*/
            db.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.e("ManageProduct", "Error al crear la base de datos: " + ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL(ManageProductContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(ManageProductContract.CategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(ManageProductContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteException ex) {
            Log.e("ManageProduct", "Error al actualizar la base de datos: " + ex.getMessage());
        } finally {
            if (db.inTransaction())
                db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,newVersion,oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    public SQLiteDatabase open(){
        return getWritableDatabase();
    }
}
