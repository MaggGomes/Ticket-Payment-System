package com.cmov.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getTransactions() {
        return "Returns all transactions";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTransactionById(@PathVariable String id) {
        return id;
    }
}
