package com.liam.softwaredesign.controller;

import com.liam.softwaredesign.models.*;
import com.liam.softwaredesign.service.SoftwareDesign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Slf4j
public class SoftwareController {

    @Autowired
    SoftwareDesign softwareDesign;

    @PostMapping("/insertClient")
    public Clients insertClient(@RequestBody Clients client){
        log.info("Inside Insert Client API");
        return softwareDesign.insertNewClient(client);
    }

    @PostMapping("/authenticate")
    public Clients login(@RequestBody Clients clients){
        log.info("Entering authenticate API");
        return softwareDesign.authenticate(clients.getUser(), clients.getPassword());
    }

    @PutMapping("/updateClient")
    public Clients updateClient(@RequestBody Clients client){
        log.info("Inside Update Client API");
        return softwareDesign.updateClient(client);
    }

    @GetMapping("/quoteHistory/user")
    public FuelQuotes getUserQuoteHistory(@RequestBody FuelQuoteRequest fuelQuoteRequest){
        log.info("Inside User Fuel Quote History");

        return softwareDesign.getUserQuoteHistory(fuelQuoteRequest);

    }
    @GetMapping("/quoteHistory/all")
    public FuelQuotes getQuoteHistory(){
        log.info("Inside All Fuel Quote History");

        return softwareDesign.getAllQuoteHistory();

    }
    @PostMapping("/insertQuote")
    public FuelQuoteForm insertQuote(@RequestBody FuelQuoteForm fuelQuoteForm){
        log.info("Inside insert Fuel Quote Form");

        return softwareDesign.insertNewFuelQuote(fuelQuoteForm);
    }
}
