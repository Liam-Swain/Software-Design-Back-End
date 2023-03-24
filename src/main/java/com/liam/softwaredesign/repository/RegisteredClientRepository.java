package com.liam.softwaredesign.repository;

import com.liam.softwaredesign.models.RegisteredClient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegisteredClientRepository extends MongoRepository<RegisteredClient, String> {
    RegisteredClient save(RegisteredClient registeredClient);
    List<RegisteredClient> findAll();
}
