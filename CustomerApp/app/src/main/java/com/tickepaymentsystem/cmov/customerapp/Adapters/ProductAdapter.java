package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;
import com.tickepaymentsystem.cmov.customerapp.Singleton;
import com.tickepaymentsystem.cmov.customerapp.Utils.Constants;

import static com.tickepaymentsystem.cmov.customerapp.Utils.Constants.COFFEE;
import static java.lang.String.valueOf;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;

    public ProductAdapter(Context context){
        this.context = context;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        ImageView logo;
        TextView name;
        TextView price;
        TextView quantity;
        Button btnIncrease;
        Button btnDecrease;

        ProductViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            logo = view.findViewById(R.id.list_item_product_logo);
            name = view.findViewById(R.id.list_item_product_name);
            price = view.findViewById(R.id.list_item_product_price);
            quantity = view.findViewById(R.id.list_item_product_quantity);
            btnIncrease = view.findViewById(R.id.list_item_product_btn_plus);
            btnDecrease = view.findViewById(R.id.list_item_product_btn_minus);
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
        String price = Singleton.products.get(position).getPrice()+" €";

        switch (Singleton.products.get(position).getName()){
            case Constants.COFFEE:
                holder.logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_product_coffe));
                break;
            case Constants.SODA_DRINK:
                holder.logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_product_sodadrink));
                break;
            case Constants.SANDWICH:
                holder.logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_product_sandwich));
                break;
            case Constants.POPCORN:
                holder.logo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_product_popcorn));
                break;
            default:
                break;
        }

        holder.name.setText(Singleton.products.get(position).getName());
        holder.price.setText(price);


         // TODO - FINISH
    }

    @Override
    public int getItemCount() {
        return Singleton.products.size();
    }

    private void onBtnIncreaseAmount(int position){

    }

    private void onBtnDecreaseAmount(int position){

    }
}
