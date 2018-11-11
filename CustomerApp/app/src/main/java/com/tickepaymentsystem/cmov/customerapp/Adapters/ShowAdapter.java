package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.ShowActivity;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder.ShowViewHolder;

import java.util.List;

public class ShowAdapter extends ArrayAdapter<Show> {

    private Context context;
    private int layout;
    private List<Show> shows;


    public ShowAdapter(@NonNull Context context, int resource, @NonNull List<Show> shows) {
        super(context, resource, shows);
        this.context = context;
        this.layout = resource;
        this.shows = shows;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShowViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);

            ((Button)convertView.findViewById(R.id.list_item_show_see_details)).setOnClickListener((View v)->onBtnSeeDetails(position));
        } else {
            holder = (ShowViewHolder)convertView.getTag();
        }



        /*Show show = shows.get(position);
        String price = Double.toString(show.getPrice()) + " €";
        ((TextView)row.findViewById(R.id.name)).setText(s.getName());
        ((TextView)row.findViewById(R.id.date)).setText(s.getDate());
        ((TextView)row.findViewById(R.id.price)).setText(price);      */

        return convertView;
    }

    private void onBtnSeeDetails(int position) {
        Toast.makeText(getContext(), "View Details" + position, Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context, ShowActivity.class));
    }
}
