package com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;

public class ShowViewHolder extends RecyclerView.ViewHolder{
    private TextView name;
    private TextView description;
    private TextView price;
    private ImageView image;
    private Button btnSeeDetails;

    public ShowViewHolder(View view){
        super(view);
        this.name = ((TextView)view.findViewById(R.id.list_item_show_name));
        this.description = ((TextView)view.findViewById(R.id.list_item_show_description));
        this.price = ((TextView)view.findViewById(R.id.list_item_show_price));
        this.image = ((ImageView)view.findViewById(R.id.list_item_show_image));
        this.btnSeeDetails = ((Button)view.findViewById(R.id.list_item_show_see_details));
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setBtnSeeDetails(Button btn) {
        this.btnSeeDetails = btn;
    }

    public Button getBtnSeeDetails() {
        return btnSeeDetails;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
