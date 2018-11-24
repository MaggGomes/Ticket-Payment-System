package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private Context context;

    public TicketAdapter(Context context){
        this.context = context;
    }

    class TicketViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView name;
        TextView date;
        TextView seatNumber;
        CheckBox checkBox;

        TicketViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_ticket_name);
            date = view.findViewById(R.id.list_item_ticket_date);
            seatNumber = view.findViewById(R.id.list_item_ticket_seat_number);
            checkBox = view.findViewById(R.id.list_item_ticket_checkbox);
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

        SimpleDateFormat month_date = new SimpleDateFormat("MMM dd", Locale.UK);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sdf.parse(Singleton.tickets.get(position).getShowDate());
            String formateDate= month_date.format(date);
            holder.date.setText(formateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.seatNumber.setText(Integer.toString(Singleton.tickets.get(position).getSeatNumber()));

        if(Singleton.tickets.get(position).isUsed()){
            holder.checkBox.setVisibility(View.INVISIBLE);
        } else {
            holder.checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        Singleton.tickets.get(position).setPicked(true);
                    }
                    else {
                        Singleton.tickets.get(position).setPicked(false);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Singleton.tickets.size();
    }
}
