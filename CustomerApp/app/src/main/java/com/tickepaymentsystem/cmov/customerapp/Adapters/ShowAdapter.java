package com.tickepaymentsystem.cmov.customerapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tickepaymentsystem.cmov.customerapp.Models.Show;
import com.tickepaymentsystem.cmov.customerapp.R;

import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {

    private List<Show> dataList;
    private Context context;

    public ShowAdapter(Context context, List<Show> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {

        public final View view;

        TextView name;
        TextView description;
        TextView price;
        private ImageView image;

        ShowViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.list_item_show_name);
            description = view.findViewById(R.id.list_item_show_description);
            price = view.findViewById(R.id.list_item_show_price);
            image = view.findViewById(R.id.list_item_show_image);
        }
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_show, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        holder.name.setText(dataList.get(position).getName());
        holder.description.setText(dataList.get(position).getDescription());

        // TODO - Format price
        holder.price.setText(dataList.get(position).getPrice().toString());

        Picasso.get()
                .load(dataList.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
