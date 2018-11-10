package com.tickepaymentsystem.cmov.customerapp.ViewHolder;

import android.widget.Button;
import android.widget.TextView;

public class ShowsViewHolder {
    private TextView name;
    private Button btnViewDetails;

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setBtnViewDetails(Button btn) {
        this.btnViewDetails = btn;
    }

    public Button getBtnViewDetails() {
        return btnViewDetails;
    }
}
