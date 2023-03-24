package com.liam.softwaredesign.serviceImpl;


import com.liam.softwaredesign.Utils.MailSenderUtils;
import com.liam.softwaredesign.Utils.MailSenderUtilsTest;
import com.liam.softwaredesign.models.Clients;
import com.liam.softwaredesign.models.FuelQuoteForm;
import com.liam.softwaredesign.models.FuelQuoteRequest;
import com.liam.softwaredesign.models.FuelQuotes;
import com.liam.softwaredesign.repository.ClientRepository;
import com.liam.softwaredesign.repository.FuelQuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class SoftwareDesignImplTest {

    @InjectMocks
    SoftwareDesignImpl softwareDesign;

    @Mock
    ClientRepository clientRepository;

    @Mock
    FuelQuoteRepository fuelQuoteRepository;

    @Mock
    MailSenderUtils mailSenderUtils;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());

        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(clients);

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.updateClient(clients.get(0));

        assertNotNull(response);
        assertEquals(response, clients.get(0));
    }

    @Test
    void updateInvalidNameClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());
        clients.get(0).setName("lllllllllllllllllllllllllllllllllllllllllllllllllll");
        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(clients);

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.updateClient(clients.get(0));

        assertNull(response);
    }

    @Test
    void updateInvalidCityClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());
        clients.get(0).setCity("lllllllllllllllllllllllllllllllllllllllllllllllllll");
        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(clients);

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.updateClient(clients.get(0));

        assertNull(response);
    }

    @Test
    void updateInvalidStateClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());
        clients.get(0).setCity("llllllllllllllll");
        clients.get(0).setState("llllllllllllllllllllllllllllllllllllllllllllllll");
        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(clients);

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.updateClient(clients.get(0));

        assertNull(response);
    }

    @Test
    void updateInvalidZipClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());
        clients.get(0).setCity("llllllllllllllll");
        clients.get(0).setZipcode("ab");
        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(clients);

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.updateClient(clients.get(0));

        assertNull(response);
    }

    @Test
    void updateNotFoundClient(){
        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());
        clients.get(0).setUser("notExist@hotmail.com");
        List<Clients> emptyList = new ArrayList<>();
        when(clientRepository.findByUser(clients.get(0).getUser())).thenReturn(emptyList);

        Clients response = softwareDesign.updateClient(generateClient());

        assertNull(response);

    }

    @Test
    void insertExistingClient(){
        Clients request = generateClient();
        when(clientRepository.findAll()).thenReturn(generateClients());

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.insertNewClient(request);

        assertNull(response);
    }

    @Test
    void insertNewClient(){
        Clients request = generateClient();
        request.setUser("liam.v.swain@hotmail.com");
        when(clientRepository.findAll()).thenReturn(generateClients());

        doNothing().when(mailSenderUtils).sendEmail("test@hotmail.com", "test subject", "test body");

        when(clientRepository.save(any())).thenReturn(new Clients());

        Clients response = softwareDesign.insertNewClient(request);

        assertNotNull(response);
        assertEquals(request, response);
    }

    @Test
    void insertNewFuelQuote(){
        FuelQuoteForm fuelQuoteForm = generateFuelQuoteForm();

        when(fuelQuoteRepository.findAll()).thenReturn(new ArrayList<FuelQuoteForm>());

        FuelQuoteForm response = softwareDesign.insertNewFuelQuote(fuelQuoteForm);

        assertNotNull(response);
        assertEquals(fuelQuoteForm, response);
    }

    @Test
    void insertSameFuelQuote(){
        FuelQuoteForm fuelQuoteForm = generateFuelQuoteForm();
        List<FuelQuoteForm> fuelQuoteFormList = new ArrayList<>();
        fuelQuoteFormList.add(fuelQuoteForm);
        when(fuelQuoteRepository.findAll()).thenReturn(fuelQuoteFormList);

        FuelQuoteForm response = softwareDesign.insertNewFuelQuote(fuelQuoteForm);
        assertNull(response);
    }


    @Test
    void getUserQuoteHistory(){
        FuelQuoteRequest fuelQuoteRequest = new FuelQuoteRequest();
        fuelQuoteRequest.setUsername("liam.v.swain@hotmail.com");

        FuelQuotes fuelQuotes = generateFuelQuotes();

        when(fuelQuoteRepository.findByUser(fuelQuoteRequest.getUsername())).thenReturn(fuelQuotes.getFuelQuotesFormList());

        FuelQuotes response = softwareDesign.getUserQuoteHistory(fuelQuoteRequest);

        assertNotNull(response);
        assertEquals(response, fuelQuotes);
    }

    @Test
    void getNoUserQuoteHistory(){
        FuelQuoteRequest fuelQuoteRequest = new FuelQuoteRequest();
        fuelQuoteRequest.setUsername("test@hotmail.com");

        FuelQuotes fuelQuotes = generateFuelQuotes();

        when(fuelQuoteRepository.findByUser(fuelQuoteRequest.getUsername())).thenReturn(null);

        FuelQuotes response = softwareDesign.getUserQuoteHistory(fuelQuoteRequest);

        assertNull(response.getFuelQuotesFormList());

        assertNotEquals(response, fuelQuotes);

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


    @Test
    void getAllQuoteHistory(){
        FuelQuotes fuelQuotes = generateFuelQuotes();

        when(fuelQuoteRepository.findAll()).thenReturn(fuelQuotes.getFuelQuotesFormList());

        FuelQuotes response = softwareDesign.getAllQuoteHistory();

        assertNotNull(response);
        assertEquals(response, fuelQuotes);

    }

    @Test
    void loginExist(){
        String username = "test@hotmail.com";
        String password = "password";

        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());

        when(clientRepository.findByUser(username)).thenReturn(clients);

        Clients response = softwareDesign.authenticate(username, password);

        assertNotNull(response);
        assertEquals(response, clients.get(0));
    }

    @Test
    void loginDoesNotExist(){
        String username = "";
        String password = "";
        Clients clients = softwareDesign.authenticate(username, password);

        assertNull(clients);
    }

    @Test
    void wrongAccount(){
        String username = "test@hotmail.com";
        String password = "password2";

        List<Clients> clients = new ArrayList<>();
        clients.add(generateClient());

        when(clientRepository.findByUser(username)).thenReturn(clients);

        Clients response = softwareDesign.authenticate(username, password);

        assertNull(response);
        assertNotEquals(response, clients.get(0));
    }

    @Test
    void getNoQuoteHistory(){
        FuelQuotes fuelQuotes = new FuelQuotes();
        fuelQuotes.setFuelQuotesFormList(null);

        when(fuelQuoteRepository.findAll()).thenReturn(fuelQuotes.getFuelQuotesFormList());

        FuelQuotes response = softwareDesign.getAllQuoteHistory();

        assertNull(response.getFuelQuotesFormList());
        assertEquals(response, fuelQuotes);

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

    private List<Clients> generateClients(){
        List<Clients> clientsList = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Clients clients = new Clients();
            clients.setName("test");
            clients.setPassword("password");
            clients.setUser("test@hotmail.com");
            clients.setRoles(null);
            clients.setActive("disabled");
            clients.setState("Tx");
            clients.setZipcode(Integer.toString(i));
            clients.setAddress1("123 Main Street");
            clients.setAddress2("123 Second Street");

            clientsList.add(clients);

        }

        return clientsList;
    }



}
