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

public class CafetariaTransactionAdapter extends RecyclerView.Adapter<CafetariaTransactionAdapter.CafetariaTransactionViewHolder> {

    private Context context;

    public CafetariaTransactionAdapter(Context context){
        this.context = context;
    }

    class CafetariaTransactionViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView name;
        TextView total;

        CafetariaTransactionViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_cafetaria_date);
            total = view.findViewById(R.id.list_item_cafetaria_total);
        }
    }

    @Override
    public CafetariaTransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_transaction_cafetaria, parent, false);
        return new CafetariaTransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CafetariaTransactionViewHolder holder, int position) {
        String totalPrice = Singleton.orderTransactions.get(position).getTotalPrice() + " €";
        holder.name.setText(Singleton.orderTransactions.get(position).getDate());
        holder.total.setText(totalPrice);
    }

    @Override
    public int getItemCount() {
        return Singleton.orderTransactions.size();
    }
}
