package com.liam.softwaredesign.repository;

import com.liam.softwaredesign.models.Clients;
import com.liam.softwaredesign.models.FuelQuoteForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FuelQuoteHistory extends MongoRepository<FuelQuoteForm, String> {

    FuelQuoteForm save(FuelQuoteForm fuelQuoteForm);

    List<FuelQuoteForm> findAll();

    List<FuelQuoteForm> findByUser(String user);


}
