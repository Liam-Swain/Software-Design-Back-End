package com.liam.softwaredesign.serviceImpl;

import com.liam.softwaredesign.Utils.MailSenderUtils;
import com.liam.softwaredesign.models.*;
import com.liam.softwaredesign.repository.ClientRepository;
import com.liam.softwaredesign.repository.FuelQuoteRepository;
import com.liam.softwaredesign.service.SoftwareDesign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import java.security.*; 
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

import java.util.List;

@Slf4j
@Configuration
public class SoftwareDesignImpl implements SoftwareDesign {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MailSenderUtils mailSender;


    @Autowired
    FuelQuoteRepository fuelQuoteRepository;

    @Override
    public String encodePass(String password){
        String givenPass = password;
        String returnPass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytePass = md.digest(givenPass.getBytes());
            BigInteger no = new BigInteger(1, bytePass);
            returnPass = no.toString(16);
            while (returnPass.length() < 32){  
                returnPass = "0" + returnPass;  
            }   
        }
        catch (NoSuchAlgorithmException e){  
            e.printStackTrace();  
        }  
        return returnPass;
    }

    @Override
    public Clients insertNewClient(Clients requestBody) {
        Clients newClient = requestBody;
        List<Clients> clientsList = clientRepository.findAll();
        boolean alreadyExist = false;

        newClient.setPassword(encodePass(newClient.getPassword()));

        for(int i = 0; i < clientsList.size(); i++){
            if(clientsList.get(i).getUser().toLowerCase().compareTo(newClient.getUser().toLowerCase()) == 0){
                alreadyExist = true;
            }
        }

        if(!alreadyExist){
            clientRepository.save(newClient);
        }
        else{
            return null;
        }

        return newClient;
    }

    @Override
    public Clients authenticate(String username, String password) {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return null;
        }
        List<Clients> clients = clientRepository.findByUser(username);

        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getPassword().compareTo(encodePass(password)) == 0){
                return clients.get(i);
            }
        }
        log.info("incorrect username or password");
        return null;
    }

    @Override
    public Clients updateClient(Clients requestBody) {
        List<Clients> clients = clientRepository.findByUser(requestBody.getUser());

        if(clients.size() == 0){
            log.info("No Client Exist With This Name");
            return null;
        }

        if(requestBody.getName().length() > 50){
            log.info("Full Name Is Not Valid");
            return null;
        }
        if(requestBody.getAddress1().length() > 100){
            log.info("Address 1 Is Not Valid");
            return null;
        }

        if(requestBody.getCity().length() > 50){
            log.info("City not valid");
            return null;
        }

        if(requestBody.getState().length() < 1 || requestBody.getState() == null){
            log.info("State code not valid");
            return null;
        }

        if(requestBody.getZipcode().length() < 5 || requestBody.getZipcode().length() > 9){
            log.info(" Zipcode not valid");
            return null;
        }

        if(requestBody.getZipcode().matches(".*[a-zA-Z]+.*")){
            log.info(" Zipcode contains letters");
            return null;
        }


        if(clients.size() == 0){
            return null;
        }
        else{
            Clients clt = clients.get(0);
            clt.setName(requestBody.getName());
            clt.setAddress1(requestBody.getAddress1());
            clt.setAddress2(requestBody.getAddress2());
            clt.setState(requestBody.getState());
            clt.setZipcode(requestBody.getZipcode());
            clt.setCity(requestBody.getCity());
            clt.setActive("Enabled");
            clientRepository.save(clt);
            return clt;
        }
    }

    @Override
    public FuelQuoteForm insertNewFuelQuote(FuelQuoteForm fuelQuoteForm){

        List<FuelQuoteForm> fuelQuoteFormList = fuelQuoteRepository.findAll();

        for(int i = 0; i < fuelQuoteFormList.size(); i++){
            if(fuelQuoteFormList.get(i).equals(fuelQuoteForm)){
                return null;
            }
        }

        fuelQuoteRepository.save(fuelQuoteForm);

        return fuelQuoteForm;
    }

    @Override
    public FuelQuotes getUserQuoteHistory(FuelQuoteRequest fuelQuoteRequest) {
        FuelQuotes fuelQuotes = new FuelQuotes();

        List<FuelQuoteForm> fuelQuoteFormList = fuelQuoteRepository.findByUser(fuelQuoteRequest.getUsername());

        fuelQuotes.setFuelQuotesFormList(fuelQuoteFormList);

        return fuelQuotes;
    }

    @Override
    public FuelQuotes getAllQuoteHistory() {
        FuelQuotes fuelQuotes = new FuelQuotes();

        List<FuelQuoteForm> fuelQuoteFormList = fuelQuoteRepository.findAll();

        fuelQuotes.setFuelQuotesFormList(fuelQuoteFormList);

        return fuelQuotes;
    }

}
