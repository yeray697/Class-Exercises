package com.phile.yrj.a100montaditos;

import android.app.Application;

import com.phile.yrj.a100montaditos.model.Montadito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 12/12/16.
 */

public class Montaditos_Application extends Application{
    private List<Montadito> montaditos;
    @Override
    public void onCreate() {
        super.onCreate();
        montaditos = new ArrayList<>();
        addInitialMontaditos();
    }

    public void addInitialMontaditos() {
        montaditos.clear();
        montaditos.add(new Montadito(8,"Pollo con alioli", 1));
        montaditos.add(new Montadito(13,"Carne mechada", 1));
        montaditos.add(new Montadito(21,"Tortilla de patatas y alioli", 1));
        montaditos.add(new Montadito(77,"Salmón ahumado", 1.5));
        montaditos.add(new Montadito(83,"Calamares", 1.5));
        montaditos.add(new Montadito(87,"Cuatro quesos", 1.5));
        montaditos.add(new Montadito(89,"Crema de chocolate con cookies and cream", 1.5));
        montaditos.add(new Montadito(90,"Crema de chocolate con leche condensada", 1.5));
        montaditos.add(new Montadito(95,"Serranito", 2));
        montaditos.add(new Montadito(98,"César", 2));
        montaditos.add(new Montadito(-1,"Coca-cola", 1));
        montaditos.add(new Montadito(-2,"Cerveza", 1));
        montaditos.add(new Montadito(-3,"Jarra", 2));
    }

    public List<Montadito> getMontaditos(){
        return montaditos;
    }

    public List<Montadito> getMontaditosBougth(){
        int amountAux;
        List<Montadito> montaditosAux = new ArrayList<>();
        for (Montadito montadito: getMontaditos()) {
            if (!montadito.getAmountParsed().equals("x0"))
                montaditosAux.add(montadito);
        }

        return montaditosAux;

    }
}
