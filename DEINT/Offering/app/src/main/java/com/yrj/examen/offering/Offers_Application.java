package com.yrj.examen.offering;

import android.app.Application;

import com.yrj.examen.offering.adapter.CustomAdapter;
import com.yrj.examen.offering.model.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This cass has a list with offers and util methods
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class Offers_Application extends Application {
    List<Offer> offers;
    @Override
    public void onCreate() {
        super.onCreate();
        offers = new ArrayList<>();
        setInitialOffers();

    }

    /**
     * Set 10 offers
     */
    public void setInitialOffers() {
        offers.add(new Offer("Bici","Decathlon","11/01/2014",Offer.TYPE_SPORT,Offer.PRIORITY_IMPORTANT));
        offers.add(new Offer("Cámara Went Pro","Media Markt","10/12/2012",Offer.TYPE_ELECTRONIC,Offer.PRIORITY_NOT_IMPORTANT));
        offers.add(new Offer("Lámpara","Todo Luz","28/02/2015",Offer.TYPE_HOME,Offer.PRIORITY_VERY_IMPORTANT));
        offers.add(new Offer("IPhone 10","Amazon","01/10/2018",Offer.TYPE_ELECTRONIC,Offer.PRIORITY_VERY_IMPORTANT));
        offers.add(new Offer("Chándal","Nike Factory","13/03/2015",Offer.TYPE_SPORT,Offer.PRIORITY_VERY_IMPORTANT));
        offers.add(new Offer("Vajilla","Natur House","03/12/2015",Offer.TYPE_HOME,Offer.PRIORITY_IMPORTANT));
        offers.add(new Offer("Portátil HP","PC Componentes","23/12/2015",Offer.TYPE_ELECTRONIC,Offer.PRIORITY_IMPORTANT));
        offers.add(new Offer("Sofá","Ikea","11/01/2016",Offer.TYPE_HOME,Offer.PRIORITY_NOT_IMPORTANT));
        offers.add(new Offer("Chándal de Nadal","Hobby Sports","25/08/2016",Offer.TYPE_SPORT,Offer.PRIORITY_NOT_IMPORTANT));
    }

    /**
     * Get offers already filtered
     * @param home Home offers will be showed
     * @param electronic Electronic offers will be showed
     * @param sport Sports offers will be showed
     * @param filter Order the list
     * @return Return the list filtered
     */
    public List<Offer> getOffers(boolean home, boolean electronic, boolean sport, int filter) {
        ArrayList<Offer> offersFiltred = new ArrayList<>();
        int typeAux;
        boolean add;
        for (int i = 0; i < offers.size(); i++) {
            add = false;
            typeAux = offers.get(i).getType();
            if (typeAux == Offer.TYPE_HOME) {
                if (home)
                    add = true;
            } else if (typeAux == Offer.TYPE_ELECTRONIC) {
                if (electronic)
                    add = true;
            } else { //TYPE_SPORT
                if (sport)
                    add = true;
            }

            if (add)
                offersFiltred.add(offers.get(i));
        }
        if (filter == CustomAdapter.FILTER_ASC) {
            Collections.sort(offersFiltred,Offer.FILTER_ASC);
        } else if (filter == CustomAdapter.FILTER_DESC) {
            Collections.sort(offersFiltred,Offer.FILTER_DESC);
        } else if (filter == CustomAdapter.FILTER_TYPE) {
            Collections.sort(offersFiltred,Offer.FILTER_TYPE);
        }
        return offersFiltred;
    }

    /**
     * Check if there is an offer with that name
     * @param name Offer's name
     * @return Check if there is an offer with that name
     */
    public boolean isDuplicated(String name) {
        boolean result = false;
        for (Offer offer:offers){
            if (offer.getName().equalsIgnoreCase(name)){
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Add an offer to the main list
     * @param offer Offer to add
     */
    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}
