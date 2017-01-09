package com.yrj.examen.offering.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yrj.examen.offering.Offers_Application;
import com.yrj.examen.offering.R;
import com.yrj.examen.offering.model.Offer;

import java.util.ArrayList;

/**
 * This class is used as adapter to the list
 * @author Yeray Ruiz Juárez
 * @version 1.0
 */
public class CustomAdapter extends ArrayAdapter<Offer> {

    boolean home, electronic, sport, priority;
    public static final int NO_FILTER = 0;
    public static final int FILTER_ASC = 1;
    public static final int FILTER_DESC = 2;
    public static final int FILTER_TYPE = 3;

    public CustomAdapter(Context context, boolean home, boolean electronic, boolean sport, boolean priority, int filter) {
        super(context,
                R.layout.item_list,
                new ArrayList<Offer>(((Offers_Application) context.getApplicationContext()).getOffers(home, electronic, sport, filter)));
        this.home = home;
        this.electronic = electronic;
        this.sport = sport;
        this.priority = priority;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent,false);
            holder.ivType = (ImageView) view.findViewById(R.id.ivType_item);
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle_item);
            holder.tvDate = (TextView) view.findViewById(R.id.tvDate_item);
            holder.tvStore = (TextView) view.findViewById(R.id.tvStore_item);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        Offer aux = getItem(position);
        if (aux.getType() == Offer.TYPE_HOME) {
            holder.ivType.setImageResource(R.mipmap.ic_home);
        } else if (aux.getType() == Offer.TYPE_ELECTRONIC) {
            holder.ivType.setImageResource(R.mipmap.ic_mobile);
        } else {
            holder.ivType.setImageResource(R.mipmap.ic_sports);
        }
        holder.tvTitle.setText(aux.getName());
        holder.tvDate.setText(aux.getDate());
        holder.tvStore.setText(aux.getStore());
        if (priority) {
            if (aux.getPriority() == Offer.Importancia.PRIORITY_NOT_IMPORTANT) {
                view.setBackgroundColor(getContext().getResources().getColor(R.color.colorLowPriority));
            } else if (aux.getPriority() == Offer.Importancia.PRIORITY_IMPORTANT) {
                view.setBackgroundColor(getContext().getResources().getColor(R.color.colorMediumPriority));
            } else {
                view.setBackgroundColor(getContext().getResources().getColor(R.color.colorHighPriority));
            }
        }
        return view;
    }

    /**
     * Change the list and show it with the new filter
     * @param filter
     */
    public void filterList(int filter) {
        clear();
        addAll(new ArrayList<Offer>(((Offers_Application) getContext().getApplicationContext()).getOffers(home, electronic, sport, filter)));
        notifyDataSetChanged();
    }

    /**
     * HolderView class
     */
    class Holder {
        ImageView ivType;
        TextView tvTitle;
        TextView tvDate;
        TextView tvStore;

    }
}
