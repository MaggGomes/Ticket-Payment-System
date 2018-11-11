package com.tickepaymentsystem.cmov.customerapp.Adapters.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tickepaymentsystem.cmov.customerapp.R;

public class CafetariaViewHolder extends RecyclerView.ViewHolder{
    private TextView name;
    private TextView quantity;
    private Button btnMinus;
    private Button btnPlus;

    public CafetariaViewHolder(View view){
        super(view);
        this.name = ((TextView)view.findViewById(R.id.list_item_cafetaria_name));
        this.quantity = ((TextView)view.findViewById(R.id.list_item_cafetaria_quantity));
        this.btnMinus = ((Button)view.findViewById(R.id.list_item_cafetaria_btn_minus));
        this.btnPlus = ((Button)view.findViewById(R.id.list_item_cafetaria_btn_plus));
    }

    public TextView getName() {
        return name;
    }

    public TextView getQuantity(){
        return quantity;
    }

    public Button getBtnMinus() {
        return btnMinus;
    }

    public Button getBtnPlus() {
        return btnPlus;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setQuantity(TextView quantity){
        this.quantity = quantity;
    }

    public void setBtnMinus(Button btn) {
        this.btnMinus = btn;
    }

    public void setBtnPlus(Button btn) {
        this.btnPlus = btn;
    }
}
