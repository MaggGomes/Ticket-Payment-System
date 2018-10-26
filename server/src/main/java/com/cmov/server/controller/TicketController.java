package com.cmov.server.controller;

import com.cmov.server.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getTickets() {
        return "Returns all tickets";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTicketById(@PathVariable String id) {
        return id;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Ticket> buyTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/validate/", method = RequestMethod.PUT)
    public ResponseEntity<String> validateTicket(@PathVariable String id) {
        return new ResponseEntity<String>("ticket validated", HttpStatus.OK);
    }
}
