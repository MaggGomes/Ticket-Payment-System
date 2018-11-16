package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder.TicketViewHolder;
import com.tickepaymentsystem.cmov.customerapp.Models.Ticket;
import com.tickepaymentsystem.cmov.customerapp.TicketQRCodeActivity;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

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
            holder.getBtnGenerateQRCode().setOnClickListener((View v) -> onBtnGenerateQRCode(position));
        } else {
            holder = (TicketViewHolder)convertView.getTag();
        }

        return convertView;
    }

    private void onBtnGenerateQRCode(int position) {
        // TODO - DELETE
        String userUUID = "a4056f64-e5bc-11e8-9f32-f2801f1b9fd1";

        // TODO - Convert to byte; pass also private key
        // Tem de enviar: The transmitted info must contain the user id, the number of tickets,
        //the tickets’ IDs and the show date e também a private key do user?
        String qrcode = userUUID+4+tickets.get(position).getId()+tickets.get(position).getShowDate();

        Intent intent = new Intent(context, TicketQRCodeActivity.class);
        intent.putExtra(Constants.CAFETARIA_ORDER, qrcode);
        context.startActivity(intent);
    }
}
