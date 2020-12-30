package org.adekeagle.repository;

import org.adekeagle.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryClientRepositoryClientTest {

    private InMemoryClientRepository repository;
    private Set<Client> clients;

    @BeforeEach
    public void setup(){
        clients = new HashSet<>();
        repository = new InMemoryClientRepository(clients);
    }

    @Test
    public void verifyIfUserIsAddingCorrectlyToTheRepository(){
        //given
        final Client client = new Client("Alek", "a@a.pl", 100);
        final Client expectedClient = new Client("Alek", "a@a.pl", 100);
        //when
        repository.save(client);
        //then
        final Client actualClient = clients.stream().findFirst().get();
//        assert actualClient.equals(expectedClient);
        assertEquals(expectedClient, actualClient);
    }
}
