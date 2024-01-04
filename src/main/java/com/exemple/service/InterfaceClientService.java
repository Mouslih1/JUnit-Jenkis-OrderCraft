package com.exemple.service;

import com.exemple.entity.Client;
import com.exemple.entity.User;

import java.util.List;

public interface InterfaceClientService {
    List<Client> getClients();
    Client addClient(Client client);
    Client updateClient(Client client);
    void delete(Long id);
    Client getById(Long id);
}
