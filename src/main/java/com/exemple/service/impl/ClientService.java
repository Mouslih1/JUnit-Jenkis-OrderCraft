package com.exemple.service.impl;

import com.exemple.entity.Client;
import com.exemple.repository.InterfaceClientRepository;
import com.exemple.service.InterfaceClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService implements InterfaceClientService {

    private InterfaceClientRepository interfaceClientRepository;

    @Override
    public List<Client> getClients()
    {
        return interfaceClientRepository.findAll();
    }

    @Override
    public Client addClient(Client client)
    {
        return interfaceClientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client)
    {
        return interfaceClientRepository.save(client);
    }

    @Override
    public void delete(Long id)
    {
        interfaceClientRepository.deleteById(id);
    }

    @Override
    public Client getById(Long id)
    {
        return interfaceClientRepository.findById(id).orElse(null);
    }
}
