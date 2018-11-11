package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layout;
    private List<String> shows;


    public TicketAdapter(@NonNull Context context, int resource, @NonNull List<String> shows) {
        super(context, resource, shows);
        this.context = context;
        this.layout = resource;
        this.shows = shows;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());;
            convertView = inflater.inflate(layout, parent, false);
        }

        //TODO - COMPLETE
        /*Show show = shows.get(position);
        String price = Double.toString(show.getPrice()) + " €";
        ((TextView)row.findViewById(R.id.name)).setText(s.getName());
        ((TextView)row.findViewById(R.id.date)).setText(s.getDate());
        ((TextView)row.findViewById(R.id.price)).setText(price);      */

        return convertView;
    }

    private void onBtnViewDetails(int position) {
        Toast.makeText(getContext(), "View Details" + position, Toast.LENGTH_SHORT).show();
    }
}
