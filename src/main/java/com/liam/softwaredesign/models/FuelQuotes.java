package com.liam.softwaredesign.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FuelQuotes {


    List<FuelQuoteForm> fuelQuotesFormList;

}
