package com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;

public class TicketViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private Button btnGenerateQRCode;
    private ImageView image;

    public TicketViewHolder(View view){
        super(view);
        this.name = ((TextView)view.findViewById(R.id.list_item_ticket_name));
        this.btnGenerateQRCode = ((Button)view.findViewById(R.id.list_item_ticket_generate_qrcode));
        this.image = ((ImageView)view.findViewById(R.id.list_item_ticket_image));
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public Button getBtnGenerateQRCode() {
        return btnGenerateQRCode;
    }

    public void setBtnGenerateQRCode(Button btnGenerateQRCode) {
        this.btnGenerateQRCode = btnGenerateQRCode;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
