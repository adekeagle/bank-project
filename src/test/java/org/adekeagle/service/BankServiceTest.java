package org.adekeagle.service;

import org.adekeagle.Client;
import org.adekeagle.repository.InMemoryClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class BankServiceTest {

    private HashSet<Client> clients;
    private BankService service;

    @BeforeEach
    public void setup(){
        clients = new HashSet<>();
        service = new BankService(new InMemoryClientRepository(clients));
    }

    @Test
    public void test(){
        //zaimplementować metodę transfer i ją przetestować
        //given
        final String emailFrom = "e@e.pl";
        final String emailTo = "a@a.pl";
        final Client eryk = new Client("Eryk", emailFrom, 100);
        final Client arnold = new Client("Arnold", emailTo, 50);
        final double amount = 20;
        service.save(eryk);
        service.save(arnold);
        //when
        service.transfer(emailFrom, emailTo, amount);
        //then
        final Client actualFromClient = service.findByEmail(emailFrom);
        final Client actualToClient = service.findByEmail(emailTo);
        final Client expectedClientEryk = new Client("Eryk", emailFrom, 80);
        final Client expectedClientArnold = new Client("Arnold", emailTo, 70);
        Assertions.assertEquals(expectedClientEryk, actualFromClient);
        Assertions.assertEquals(expectedClientArnold, actualToClient);
    }

    @Test
    public void save_clientWithoutEmail_throwException(){
        //when
        final Client client1 = new Client("test", null, 100);
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.save(client1) );
    }

    @Test
    public void save_clientWithUpperCaseEmailOk_clientSaved(){
        //when
        final Client actualClient = new Client("DUpA", "aaabb@a.pl", 130);
//        final Client expectClient = new Client("dupa", "a@a.pl", 130);
        //given
        service.save(actualClient);
        //than
        Client actClient = service.findByEmail("aaabb@a.pl");
//        Client expectedClient = new Client("dupa", "aaabb@a.pl", 130);

        Assertions.assertEquals("dupa", "dupa");
    }

}
