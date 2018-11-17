package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;

    public ProductAdapter(Context context){
        this.context = context;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView name;

        ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_product_name);
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
        holder.name.setText(Singleton.products.get(position).getName());
         // TODO - FINISH
    }

    @Override
    public int getItemCount() {
        return Singleton.products.size();
    }

    private void onBtnMakeOrder(int position) {


        // TODO - Implement
    }
}
