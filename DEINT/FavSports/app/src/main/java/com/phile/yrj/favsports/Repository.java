package com.phile.yrj.favsports;

import android.content.SharedPreferences;

import com.phile.yrj.favsports.model.Sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by usuario on 23/11/16.
 */
public class Repository {
    private static List<Sport> sports;
    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        sports = new ArrayList<Sport>();
        sports.add(new Sport("Baloncesto",R.raw.icon_basketball,false));
        sports.add(new Sport("Fútbol",R.raw.icon_beach_soccer,false));
        sports.add(new Sport("Boxeo",R.raw.icon_boxing,false));
        sports.add(new Sport("Ajedrez",R.raw.icon_chess,false));
        sports.add(new Sport("Cricket",R.raw.icon_cricket,false));
        sports.add(new Sport("Curling",R.raw.icon_curling,false));
        sports.add(new Sport("Ciclismo",R.raw.icon_cycling,false));
        sports.add(new Sport("Dardos",R.raw.icon_darts,false));
        sports.add(new Sport("Damas",R.raw.icon_daughts,false));
        sports.add(new Sport("FloorBall",R.raw.icon_floorball,false));
    }

    /**
     * Get the sports used in the list
     * @return Return the sports
     */
    public List<Sport> getSports(){
        return sports;
    }

    /**
     * Set the favorite on sports
     * @param sharedPreferences Shared preferences where are saved
     */
    public void setFavSports(SharedPreferences sharedPreferences) {
        int count = 0;
        for (Sport sportAux: sports) {
            sportAux.setFavorite(sharedPreferences.getBoolean(sportAux.getName(),false));
            count ++;
        }
    }
}
