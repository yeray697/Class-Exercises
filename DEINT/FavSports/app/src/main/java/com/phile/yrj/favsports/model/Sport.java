package com.phile.yrj.favsports.model;

import java.io.Serializable;

/**
 * Created by usuario on 23/11/16.
 */

public class Sport implements Serializable {
    private String name;
    private int image;
    private boolean favorite;

    public Sport (String name, int image, boolean favorite) {
        this.setName(name);
        this.setImage(image);
        this.setFavorite(favorite);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
