package com.phile.yrj.favsports.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.favsports.R;
import com.phile.yrj.favsports.Repository;
import com.phile.yrj.favsports.model.Sport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/11/16.
 */

public class CustomListAdapter extends ArrayAdapter<Sport> implements Serializable{
    Context context;
    public CustomListAdapter(Context context) {
        super(context, R.layout.sports_item, new ArrayList<Sport>(Repository.getInstance().getSports()));
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SportHolder holder = new SportHolder();
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sports_item,null);

            holder = new SportHolder();
            holder.ivSport = (ImageView) view.findViewById(R.id.ivSport_item);
            holder.tvSport = (TextView) view.findViewById(R.id.tvSport_item);
            holder.cbSport = (CheckBox) view.findViewById(R.id.cbSport_item);
            view.setTag(holder);
        } else {
            holder = (SportHolder) view.getTag();
        }
        holder.cbSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getItem(position).setFavorite(b);

            }
        });
        Sport sportAux = getItem(position);
        holder.ivSport.setImageResource(sportAux.getImage());
        holder.tvSport.setText(sportAux.getName());
        holder.cbSport.setChecked(sportAux.isFavorite());
        return view;
    }

    /**
     * Filter the list with the char passed
     * @param character Char used to filter the list
     */
    public void filterByChar(String character) {
        List<Sport> sports = Repository.getInstance().getSports();
        List<Sport> newSports = new ArrayList<>();
        if (!character.equals("")) {
            character = character.toLowerCase();
            for (int i=0; i<sports.size();i++) {
                if (sports.get(i).getName().toLowerCase().startsWith(character)) {
                    newSports.add(sports.get(i));
                }
            }
            setList(newSports);
        } else {
            setList(sports);
        }
    }

    public List<Sport> getList() {
        List<Sport> sports = new ArrayList<>();
        for (int i=0; i<getCount();i++)
            sports.add(getItem(i));
        return sports;
    }

    class SportHolder{
        ImageView ivSport;
        TextView tvSport;
        CheckBox cbSport;
    }

    /**
     * Method that set a list of sports
     * @param sports
     */
    public void setList(List<Sport> sports) {
        this.clear();
        //for (int i=0; i<sports.size();i++)
        //    sports.add(getItem(i));
        addAll(sports);
        notifyDataSetChanged();
    }
}
