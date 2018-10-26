package com.cmov.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCustomer() {
        return "Returns all customers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCustomerById(@PathVariable String id) {
        return id;
    }
}
