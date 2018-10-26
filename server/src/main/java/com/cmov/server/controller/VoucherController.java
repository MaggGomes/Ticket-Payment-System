package com.cmov.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/voucher")
public class VoucherController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getVouchers() {
        return "Returns all vouchers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getVoucherById(@PathVariable String id) {
        return id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String useVoucher(@PathVariable String id) {
        return "voucher used";
    }
}
