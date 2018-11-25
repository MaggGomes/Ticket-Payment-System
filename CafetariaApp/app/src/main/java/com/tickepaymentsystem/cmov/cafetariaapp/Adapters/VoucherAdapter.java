package com.tickepaymentsystem.cmov.cafetariaapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.Product;
import com.tickepaymentsystem.cmov.cafetariaapp.Models.Voucher;
import com.tickepaymentsystem.cmov.cafetariaapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private List<Voucher> vouchers;
    private Context context;

    public VoucherAdapter(List<Voucher> vouchers, Context context){
        this.vouchers = vouchers;
        this.context = context;
    }

    class VoucherViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView name;

        VoucherViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_voucher_name);
        }
    }

    @Override
    public VoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_voucher, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VoucherViewHolder holder, int position) {
        switch (vouchers.get(position).getProductId()){
            case 0:
                holder.name.setText("5% Discount");
                break;
            case 1:
                holder.name.setText("Free coffee");
                break;
            case 2:
                holder.name.setText("Free popcorn");
                break;
            case 3:
                holder.name.setText("Free Soda drink");
                break;
            case 4:
                holder.name.setText("Free sandwich");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }
}
