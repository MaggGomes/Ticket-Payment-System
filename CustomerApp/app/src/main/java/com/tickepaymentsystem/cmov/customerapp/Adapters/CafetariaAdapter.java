package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.ViewHolder.CafetariaViewHolder;

import java.util.List;

public class CafetariaAdapter extends ArrayAdapter<String> {

    private int layout;

    public CafetariaAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CafetariaViewHolder mainCafetariaViewHolder = null;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            CafetariaViewHolder cafetariaViewHolder = new CafetariaViewHolder();
            cafetariaViewHolder.setName((TextView)convertView.findViewById(R.id.list_item_cafetaria_name));
            cafetariaViewHolder.setBtnPlus((Button)convertView.findViewById(R.id.list_item_cafetaria_btn_plus));
            cafetariaViewHolder.getBtnMinus().setOnClickListener((View v)->onBtnMinusClick(position));
            cafetariaViewHolder.getBtnPlus().setOnClickListener((View v)->onBtnPlusClick(position));
            convertView.setTag(cafetariaViewHolder);
        } else {
            mainCafetariaViewHolder = (CafetariaViewHolder)convertView.getTag();
            mainCafetariaViewHolder.getName().setText(getItem(position));
        }

        return convertView;
    }

    private void onBtnMinusClick(int position) {
        Toast.makeText(getContext(), "Minor button" + position, Toast.LENGTH_SHORT).show();
    }

    private void onBtnPlusClick(int position) {
        Toast.makeText(getContext(), "Plus button" + position, Toast.LENGTH_SHORT).show();
    }
}
