package com.liam.softwaredesign.repository;

import com.liam.softwaredesign.models.Clients;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Clients, String> {

    Clients save(Clients client);
    List<Clients> findAll();
    List<Clients> findByName(String name);

}
