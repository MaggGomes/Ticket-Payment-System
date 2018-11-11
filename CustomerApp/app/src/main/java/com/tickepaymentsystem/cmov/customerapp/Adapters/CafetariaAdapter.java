package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder.CafetariaViewHolder;

import java.util.List;

public class CafetariaAdapter extends ArrayAdapter<String> {

    private static final String TAG = CafetariaAdapter.class.getName();
    private Context context;
    private int layout;
    private List<String> cafetaria;


    public CafetariaAdapter(@NonNull Context context, int resource, @NonNull List<String> cafetaria) {
        super(context, resource, cafetaria);
        this.context = context;
        this.layout = resource;
        this.cafetaria = cafetaria;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CafetariaViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());;
            convertView = inflater.inflate(layout, parent, false);

            // Initialize ViewHolder
            holder = new CafetariaViewHolder(convertView);
            holder.getBtnMinus().setOnClickListener((View v)->onBtnMinusClick(position, -1));
            holder.getBtnPlus().setOnClickListener((View v)->onBtnPlusClick(position, 1));

            convertView.setTag(holder);
        } else {
            holder = (CafetariaViewHolder)convertView.getTag();
        }

        return convertView;
    }

    private void onBtnMinusClick(int position, int quantity) {
        Toast.makeText(getContext(), "Minor button" + position, Toast.LENGTH_SHORT).show();
        // TODO - Verificar porque não está a funcionar
        //Log.d(TAG, ((TextView)view.findViewById(R.id.list_item_cafetaria_quantity)).getText().toString());

    }

    private void onBtnPlusClick(int position, int quantity) {
        Toast.makeText(getContext(), "Plus button" + position, Toast.LENGTH_SHORT).show();
    }
}
