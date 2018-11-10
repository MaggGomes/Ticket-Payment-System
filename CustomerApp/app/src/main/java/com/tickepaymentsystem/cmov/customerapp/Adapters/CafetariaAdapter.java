package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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

    private static final String TAG = "CafetariaAdapter";
    private int layout;
    private List<String> cafetaria;


    public CafetariaAdapter(@NonNull Context context, int resource, @NonNull List<String> cafetaria) {
        super(context, resource, cafetaria);
        this.layout = resource;
        this.cafetaria = cafetaria;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());;
            convertView = inflater.inflate(layout, parent, false);
        }

        ((Button)convertView.findViewById(R.id.list_item_cafetaria_btn_minus)).setOnClickListener((View v)->onBtnMinusClick(v, position, -1));
        ((Button)convertView.findViewById(R.id.list_item_cafetaria_btn_plus)).setOnClickListener((View v)->onBtnPlusClick(v, position, +1));


        return convertView;
    }

    private void onBtnMinusClick(View view, int position, int quantity) {
        Toast.makeText(getContext(), "Minor button" + position, Toast.LENGTH_SHORT).show();
        //Log.d(TAG, ((TextView)view.findViewById(R.id.list_item_cafetaria_quantity)).getText().toString());

    }

    private void onBtnPlusClick(View view, int position, int quantity) {
        Toast.makeText(getContext(), "Plus button" + position, Toast.LENGTH_SHORT).show();
    }
}
