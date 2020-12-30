package org.adekeagle.repository;

import org.adekeagle.Client;
import org.adekeagle.repository.ClientRepository;

import java.util.Objects;
import java.util.Set;

public class InMemoryClientRepository implements ClientRepository {

    private Set<Client> clients;

    public InMemoryClientRepository(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clients.stream().filter(client -> Objects.equals(client.getEmail(), email)).findFirst().orElseThrow();

    }
}
