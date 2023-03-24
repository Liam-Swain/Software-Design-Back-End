package com.liam.softwaredesign.service;

import com.liam.softwaredesign.models.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface SoftwareDesign {


    Clients getAllClients();
    Clients findClient();
    Clients insertNewClient(Clients requestBody);
    Clients updateClient();


    RegisteredClient registerNewClient(RegisteredClient registeredClient);

    RegisteredClient getLoggedInUser();

    RegisteredClient verifyLogin(RegisteredClient registeredClient);

    FuelQuoteForm insertNewFuelQuote(FuelQuoteForm fuelQuoteForm);

    FuelQuotes getUserQuoteHistory(FuelQuoteRequest fuelQuoteRequest);

    FuelQuotes getAllQuoteHistory();
}
