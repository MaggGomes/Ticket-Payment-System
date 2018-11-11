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

import com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder.TicketViewHolder;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.ShowActivity;
import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder.ShowViewHolder;
import com.tickepaymentsystem.cmov.customerapp.TicketQRCodeActivity;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ticket> {

    private Context context;
    private int layout;
    private List<Ticket> tickets;


    public TicketAdapter(@NonNull Context context, int resource, @NonNull List<Ticket> tickets) {
        super(context, resource, tickets);
        this.context = context;
        this.layout = resource;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TicketViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);

            // Initialize ViewHolder
            holder = new TicketViewHolder(convertView);
            holder.getBtnGenerateQRCode().setOnClickListener((View v)->onBtnGenerateQRCode(position));
        } else {
            holder = (TicketViewHolder)convertView.getTag();
        }

        return convertView;
    }

    private void onBtnGenerateQRCode(int position) {
        Toast.makeText(getContext(), "View Details" + position, Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context, TicketQRCodeActivity.class));
    }
}
