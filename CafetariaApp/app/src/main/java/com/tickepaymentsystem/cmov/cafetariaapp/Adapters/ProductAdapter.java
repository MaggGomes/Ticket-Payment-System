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
import com.tickepaymentsystem.cmov.cafetariaapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private Context context;

    public ProductAdapter(List<Product> products, Context context){
        this.products = products;
        this.context = context;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView name;
        TextView quantity;
        TextView total;

        ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_product_name);
            quantity = view.findViewById(R.id.list_item_product_quantity);
            total = view.findViewById(R.id.list_item_product_total);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        String quant = "Qty: " + products.get(position).getQuantity();
        double price = products.get(position).getQuantity() * products.get(position).getPrice();
        String finalPrice = price + " €";
        holder.name.setText(products.get(position).getName());
        holder.quantity.setText(quant);
        holder.total.setText(finalPrice);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
