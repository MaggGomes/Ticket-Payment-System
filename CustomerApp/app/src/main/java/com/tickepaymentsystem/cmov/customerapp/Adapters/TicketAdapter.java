package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.TicketQRCodeActivity;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private Context context;

    public TicketAdapter(Context context){
        this.context = context;
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView name;
        Button btnGenerateQRCode;

        TicketViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_ticket_name);
            btnGenerateQRCode = view.findViewById(R.id.list_item_ticket_generate_qrcode);
        }
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        holder.name.setText(Singleton.tickets.get(position).getShowName());
        holder.btnGenerateQRCode.setOnClickListener((View v) -> onBtnGenerateQRCode(position));
    }

    @Override
    public int getItemCount() {
        return Singleton.tickets.size();
    }

    private void onBtnGenerateQRCode(int position) {
        // TODO - DELETE
        String userUUID = "a4056f64-e5bc-11e8-9f32-f2801f1b9fd1";

        // TODO - Convert to byte; pass also private key
        // Tem de enviar: The transmitted info must contain the user id, the number of tickets,
        //the tickets’ IDs and the show date e também a private key do user?
        String qrcode = userUUID+4+Singleton.tickets.get(position).getId()+Singleton.tickets.get(position).getShowDate();

        Intent intent = new Intent(context, TicketQRCodeActivity.class);
        intent.putExtra(Constants.CAFETARIA_ORDER, qrcode);
        context.startActivity(intent);
    }
}
