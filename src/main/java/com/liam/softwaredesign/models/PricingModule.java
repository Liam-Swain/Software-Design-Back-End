package com.liam.softwaredesign.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="PricingModule")
public class PricingModule {


    private String suggestedPrice;
    private String totalPrice;


}
