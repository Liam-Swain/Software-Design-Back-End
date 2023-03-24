package com.liam.softwaredesign.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "RegisteredClients")
public class RegisteredClient {
    @Id
    private String id;
    private String username;
    private String password;
}
