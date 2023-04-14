package com.liam.softwaredesign.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
public class FuelQuoteRequest {
    private String username;
    private String gallonsRequested;
    private String state;
    private String date;
}
