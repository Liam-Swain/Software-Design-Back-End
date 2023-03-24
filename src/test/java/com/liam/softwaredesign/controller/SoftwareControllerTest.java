package com.liam.softwaredesign.controller;

import com.liam.softwaredesign.models.Clients;
import com.liam.softwaredesign.models.FuelQuoteForm;
import com.liam.softwaredesign.models.FuelQuoteRequest;
import com.liam.softwaredesign.models.FuelQuotes;
import com.liam.softwaredesign.service.SoftwareDesign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SoftwareControllerTest {

    @InjectMocks
    SoftwareController softwareController;

    @Mock
    SoftwareDesign softwareDesign;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertClient(){
        Clients clients = generateClient();

        when(softwareDesign.insertNewClient(clients)).thenReturn(clients);

        Clients response = softwareController.insertClient(clients);



        assertNotNull(response);
        assertEquals(clients, response);
    }

    @Test
    void insertQuote(){
        FuelQuoteForm response = generateFuelQuoteForm();

        when(softwareDesign.insertNewFuelQuote(response)).thenReturn(response);

        FuelQuoteForm ret = softwareController.insertQuote(response);

        assertNotNull(ret);
        assertEquals(ret, response);
    }

    @Test
    void updateClient(){
        Clients clients = generateClient();

        when(softwareController.updateClient(clients)).thenReturn(clients);

        Clients response = softwareController.updateClient(clients);

        assertNotNull(response);
        assertEquals(response, clients);
    }

    @Test
    void getUserQuoteHistory(){
        FuelQuoteRequest fuelQuoteRequest = new FuelQuoteRequest();
        fuelQuoteRequest.setUsername("liam.v.swain@hotmail.com");

        FuelQuotes response = generateFuelQuotes();

        when(softwareDesign.getUserQuoteHistory(fuelQuoteRequest)).thenReturn(response);

        final FuelQuotes fuelQuotes = softwareController.getUserQuoteHistory(fuelQuoteRequest);

        assertNotNull(fuelQuotes);
        assertEquals(response, fuelQuotes);
    }

    @Test
    void getQuoteHistory(){
        FuelQuotes response = generateFuelQuotes();

        when(softwareDesign.getAllQuoteHistory()).thenReturn(response);

        FuelQuotes ret = softwareController.getQuoteHistory();

        assertNotNull(ret);
        assertEquals(response, ret);
    }

    @Test
    void getNullUserQuoteHistory(){
        FuelQuoteRequest fuelQuoteRequest = new FuelQuoteRequest();
        fuelQuoteRequest.setUsername("test@hotmail.com");

        FuelQuotes response = null;

        when(softwareDesign.getUserQuoteHistory(fuelQuoteRequest)).thenReturn(response);

        FuelQuotes ret = softwareController.getUserQuoteHistory(fuelQuoteRequest);

        assertEquals(ret, response);

    }

    @Test
    void login(){
        String username = "test@hotmail.com";
        String password = "password";

        Clients clients = generateClient();

        when(softwareDesign.authenticate(username, password)).thenReturn(clients);

        Clients response = softwareController.login(username, password);

        assertNotNull(response);
        assertEquals(response, clients);

    }

    @Test
    void getNullQuoteHistory(){
        FuelQuotes response = null;
        when(softwareDesign.getAllQuoteHistory()).thenReturn(response);

        FuelQuotes ret = softwareController.getQuoteHistory();
        assertEquals(response, ret);
    }

    private FuelQuotes generateFuelQuotes(){
        FuelQuotes fuelQuote = new FuelQuotes();

        for(int i = 0; i < 5; i++){
            FuelQuoteForm fuelQuoteForm = new FuelQuoteForm();

            fuelQuoteForm.setUser("liam.v.swain@hotmail.com");
            fuelQuoteForm.setDeliveryDate("testDate");
            fuelQuoteForm.setDeliveryAddress("123 main street");
            fuelQuoteForm.setGallonsRequested(Integer.toString(i + 1));
            fuelQuoteForm.setSuggestedPrice(Integer.toString((i + 1) * 10));
            fuelQuoteForm.setTotalAmount(Integer.toString((i + 1) * 10));

            if(fuelQuote.getFuelQuotesFormList() == null){
                List<FuelQuoteForm> fuelQuoteFormList = new ArrayList<>();
                fuelQuoteFormList.add(fuelQuoteForm);
                fuelQuote.setFuelQuotesFormList(fuelQuoteFormList);
            }
            else{
                fuelQuote.getFuelQuotesFormList().add(fuelQuoteForm);
            }
        }

        return fuelQuote;
    }

    private FuelQuoteForm generateFuelQuoteForm(){
        FuelQuoteForm fuelQuoteForm = new FuelQuoteForm();

        fuelQuoteForm.setUser("test@hotmail.com");
        fuelQuoteForm.setDeliveryDate("test date");
        fuelQuoteForm.setDeliveryAddress("123 main street");
        fuelQuoteForm.setGallonsRequested("20");
        fuelQuoteForm.setSuggestedPrice("100");
        fuelQuoteForm.setTotalAmount("100");

        return fuelQuoteForm;
    }

    private Clients generateClient(){
        Clients client = new Clients();
        client.setRoles(null);
        client.setUser("test@hotmail.com");
        client.setPassword("password");
        client.setActive("Active");
        client.setState("Tx");
        client.setAddress1("123 Main Street");
        client.setName("Test");

        return client;
    }


}
