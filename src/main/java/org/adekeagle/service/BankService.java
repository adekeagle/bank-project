package org.adekeagle.service;

import org.adekeagle.Client;
import org.adekeagle.exceptions.NoSufficientFoundException;
import org.adekeagle.repository.ClientRepository;

import java.util.Objects;

public class BankService {

    private final ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client){
        if(client.getEmail() == null || client.getName() == null || client.getBalance() < 0) {
            throw new IllegalArgumentException("Invalid argument!");
        }

        String name = client.getName().toLowerCase();
        client.setName(name);

        String mail = client.getEmail().toLowerCase();
        client.setEmail(mail);

        double balance = client.getBalance();
        client.setBalance(balance);

//        final Client byEmail = clientRepository.findByEmail(mail);

//        if(client.getEmail().contains(clientRepository.findByEmail(mail))){
//            throw new IllegalArgumentException("Client with following email " + mail + " already exists");
//        }else{
            clientRepository.save(client);
//        }
    }

    public Client findByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    public void transfer(String fromEmail, String toEmail, double amount){
        Client clientFromMail = clientRepository.findByEmail(fromEmail);
        Client clientToMail = clientRepository.findByEmail(toEmail);

        if(amount > 0 && clientFromMail.getBalance() - amount > 0) {
            clientFromMail.setBalance(clientFromMail.getBalance() - amount);
            clientToMail.setBalance(clientToMail.getBalance() + amount);
        }else {
            throw new NoSufficientFoundException("Not enough funds!");
        }
    }
}
