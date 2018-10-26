package com.cmov.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cc")
public class CreditCardController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCC() {
        return "Returns all ccs";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCCById(@PathVariable String id) {
        return id;
    }
}
