package com.liam.softwaredesign.service;

import com.liam.softwaredesign.models.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface SoftwareDesign {

    Clients insertNewClient(Clients requestBody);

    Clients authenticate(String username, String password);

    Clients updateClient(Clients requestBody);

    String encodePass(String password);

    FuelQuoteForm insertNewFuelQuote(FuelQuoteForm fuelQuoteForm);

    FuelQuotes getUserQuoteHistory(FuelQuoteRequest fuelQuoteRequest);

    FuelQuotes getAllQuoteHistory();

    PricingModule createQuote(FuelQuoteRequest fuelQuoteRequest);

}
