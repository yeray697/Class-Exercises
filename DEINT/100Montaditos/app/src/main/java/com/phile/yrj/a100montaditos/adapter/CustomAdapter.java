package com.phile.yrj.a100montaditos.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.phile.yrj.a100montaditos.Montaditos_Application;
import com.phile.yrj.a100montaditos.R;
import com.phile.yrj.a100montaditos.model.Montadito;

import java.util.ArrayList;

/**
 * Created by usuario on 12/12/16.
 */

public class CustomAdapter extends ArrayAdapter<Montadito> {
    public CustomAdapter(Context context) {
        super(context,
                R.layout.item_list,(((Montaditos_Application)context.getApplicationContext()).getMontaditos()));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Holder holder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent,false);
            holder = new Holder();
            holder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            holder.etAmount = (EditText) view.findViewById(R.id.etAmount);
            holder.btIncrease = (Button) view.findViewById(R.id.btIncrease);
            holder.btDecrease = (Button) view.findViewById(R.id.btDecrease);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        final Montadito aux = getItem(position);
        holder.tvTitle.setText(aux.toString());
        holder.etAmount.setText(aux.getAmountParsed());
        holder.btIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    aux.increaseAmount();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Sólo puedes pedir 10 por ronda", Toast.LENGTH_SHORT).show();
                }
                holder.etAmount.setText(aux.getAmountParsed());
            }
        });
        holder.btDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aux.decreaseAmount();
                holder.etAmount.setText(aux.getAmountParsed());
            }
        });
        return view;
    }
    class Holder{
        TextView tvTitle;
        EditText etAmount;
        Button btIncrease;
        Button  btDecrease;
    }
}
