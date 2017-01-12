package com.example.yrj.manageproductrecycler.model;

import com.example.yrj.manageproductrecycler.R;
import com.example.yrj.manageproductrecycler.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductRepository implements IRepository {

    private ArrayList<Product> products = new ArrayList<Product>();
    private static ProductRepository repository = new ProductRepository();

    public static ProductRepository getInstance() {
        return repository;
    }

    private ProductRepository(){
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","400 MG 30 SOBRES GRANULADO ",3.23,26, R.mipmap.ic_launcher));
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas","CINFA","600 MG 20 SOBRES GRANULADO",3.73,15,R.mipmap.ic_launcher));
        products.add(new Product("Ibuprofeno","Es un fármaco con propiedades antiinflamatorias, antipiréticas y analgésicas\"","CINFA","600 MG 40 SOBRES GRANULADO ",5.92,50,R.mipmap.ic_launcher));
        products.add(new Product("Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","100mg/5ml susp 200ml",3.48,19,R.mipmap.ic_launcher));
        products.add(new Product("Dalsy","Dalsy contiene ibuprofeno como principio activo y pertenece a un grupo de medicamentos llamados antiinflamatorios no esteroideos","ABBOTT LABORATORIES","200MG 20 sobres",7.77,10,R.mipmap.ic_launcher));
        products.add(new Product("Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 20 COMPRIMIDOS",3.36,48,R.mipmap.ic_launcher));
        products.add(new Product("Aspirina","Alivio del dolor de cabeza","BAYER","500 MG 10 COMPR MASTIC",3.67,48,R.mipmap.ic_launcher));
        products.add(new Product("Frenadol","Te ayudan a frenar los síntomas de la gripe y el resfriado","MCNEIL IBERIC","10 COMPRIMIDOS EFERVESCENTES",4.95,26, R.mipmap.ic_launcher));
        products.add(new Product("Frenadol Complex","Es un medicamento indicado para el alivio sintomático de gripes y catarros","MCNEIL IBERIC","10 SOBRES",4.95,64,R.mipmap.ic_launcher));
        products.add(new Product("Valium","Es una droga que se receta ampliamente contra la ansiedad, y de la que también se abusa mucho","ROCHE FARMA","10 MG 6 AMPOLLAS 2 ML",2.61,65,R.mipmap.ic_launcher));
    }

    public List<Product> getAllProducts(){
        return products;
    }

    @Override
    public Product getProductBy(int id) {
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {

    }
}
