package org.adekeagle.repository;

import org.adekeagle.Client;

public interface ClientRepository {
    void save(Client client);
    Client findByEmail(String email);
}
