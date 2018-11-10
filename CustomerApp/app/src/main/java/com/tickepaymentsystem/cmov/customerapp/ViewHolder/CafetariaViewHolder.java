package com.tickepaymentsystem.cmov.customerapp.ViewHolder;

import android.widget.Button;
import android.widget.TextView;

public class CafetariaViewHolder {
    private TextView name;
    private TextView quantity;
    private Button btnMinus;
    private Button btnPlus;

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
