package com.yrj.examen.offering.model;

import com.yrj.examen.offering.R;

import java.util.Comparator;

/**
 * This class is used to store an offer
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class Offer {
    //Offer priority values
    public final static int PRIORITY_NOT_IMPORTANT = 1;
    public final static int PRIORITY_IMPORTANT = 2;
    public final static int PRIORITY_VERY_IMPORTANT = 3;
    //Offer type  values
    public final static int TYPE_HOME = 1;
    public final static int TYPE_ELECTRONIC = 2;
    public final static int TYPE_SPORT = 3;

    private String name;
    private String store;
    private String date;
    private int type;
    private int priority;

    public static final Comparator<? super Offer> FILTER_ASC = new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };
    public static final Comparator<? super Offer> FILTER_DESC = new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return -1 * o1.getName().compareToIgnoreCase(o2.getName());
        }
    };
    public static final Comparator<? super Offer> FILTER_TYPE = new Comparator<Offer>() {
        @Override
        public int compare(Offer o1, Offer o2) {
            return Integer.compare(o1.getType(),o2.getType());
        }
    };

    /**
     * Constructor
     * @param name Offer's name
     * @param store Offer's store
     * @param date Offer's date
     * @param type Offer's type
     * @param priority Offer's priority
     */
    public Offer(String name, String store, String date, int type, int priority) {
        this.name = name;
        this.store = store;
        this.date = date;
        this.type = type;
        this.priority = priority;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
