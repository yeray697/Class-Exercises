package com.example.yrj.manageproduct;

import android.app.Application;

import com.example.yrj.manageproduct.model.Product;
import com.example.yrj.manageproduct.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yeray Ruiz
 */

public class LoginApplication extends Application {
    private User user;
    ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public void onCreate(){
        super.onCreate();
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","400 MG 30 SOBRES GRANULADO ",3.23,26,R.raw.ibuprofeno400g));
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","600 MG 20 SOBRES GRANULADO",3.73,15,R.raw.ibuprofeno600g));
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas\"","CINFA","600 MG 40 SOBRES GRANULADO ",5.92,50,R.raw.ibuprofeno600g2));
        products.add(new Product("Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","100mg/5ml susp 200ml",3.48,19,R.raw.dalsy100mg5ml));
        products.add(new Product("Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","200MG 20 sobres",7.77,10,R.raw.dalsy200mg));
        products.add(new Product("Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 20 COMPRIMIDOS",3.36,48,R.raw.aspirina500mg20));
        products.add(new Product("Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 10 COMPR MASTIC",3.67,48,R.raw.aspirina500mg10));
        products.add(new Product("Frenadol","Te ayudan a frenar los síntomas de la gripe y el resfriado","MCNEIL IBERIC","10 COMPRIMIDOS EFERVESCENTES",4.95,26,R.raw.frenadol));
        products.add(new Product("Frenadol Complex","Es un medicamento indicado para el alivio sintomático de gripes y catarros","MCNEIL IBERIC","10 SOBRES",4.95,64,R.raw.frenadol_complex));
        products.add(new Product("Valium","Es una droga que se receta ampliamente contra la ansiedad, y de la que también se abusa mucho","ROCHE FARMA","10 MG 6 AMPOLLAS 2 ML",2.61,65,R.raw.valium));

    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        if (this.user != user)
            this.user = user;
    }


    public List<Product> getProducts(){
        return products;
    }

    public boolean addProduct(Product product){
        boolean result = false;
        if(!products.contains(product)) {
            products.add(product);
            result = true;
        }
        return result;
    }

    public String[] getProductsName() {
        return  null;
    }

    public String[] getProductsStock() {
        return null;
    }

    public int[] getProductsImage() {
        return null;
    }
}
