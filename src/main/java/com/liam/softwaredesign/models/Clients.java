package com.liam.softwaredesign.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection="Clients")
public class Clients {

    @Id
    private String id;
    private String name;
    private String user;
    private String active;
    private List<String> roles;
    private String address1;
    private String address2;
    private String state;
    private String zipcode;
}
