package com.cmov.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/show")
public class ShowsController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getShows() {
        return "Returns all shows";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getShowById(@PathVariable String id) {
        return id;
    }
}
