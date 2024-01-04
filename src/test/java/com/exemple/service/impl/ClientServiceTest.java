package com.exemple.service.impl;

import com.exemple.config.DispatcherServletInit;
import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.Client;
import com.exemple.repository.InterfaceClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
@ContextConfiguration(classes = {DispatcherServletInit.class, PersistenceJPAConfig.class})
@ExtendWith(SpringExtension.class)
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private InterfaceClientRepository clientRepository;

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }
    @Test
    void getByIdExistingTest() {
        // Given
       Client client = new Client("Client1","gh@hhhh","casa");
        Client savedClient = clientRepository.save(client);
        // When
        Client retrievedClient = clientService.getById(savedClient.getId());
        // Then
        assertThat(retrievedClient).isEqualTo(savedClient);
    }
    @Test
    void getByIdNonExistentTest() {
        // Given
        Long nonExistentId = 999L;
        // When
       Client retrievedClient = clientService.getById(nonExistentId);
        // Then
        assertThat(retrievedClient).isNull();
    }
//    @Test
//    void getClientsTest() {
//        // Given
//        Client client = new Client("client1","123","Fes");
//        clientRepository.save(client);
//        // When
//        List<Client>clients = clientService.getClients();
//        // Then
//        assertThat(clients).hasSize(1);
//        assertThat(clients.get(0)).isEqualTo(client);
//    }

    @Test
    void addClientTest() {
        // Given
        Client client = new Client("NewClient", "135","rabat");
        // When
        Client savedClient = clientService.addClient(client);
        // Then
        assertThat(savedClient.getId()).isNotNull();
        assertThat(savedClient).isEqualToIgnoringGivenFields(client, "id");
    }
    @Test
    void updateClientTest() {
        // Given
        Client client = new Client("Client1", "hhqhh", "Taza");
        Client savedClient = clientRepository.save(client);
        // When
        savedClient.setName("UpdatedClientt");
        savedClient.setVille("casa");
        Client updatedClient = clientService.updateClient(savedClient);
        // Then
        assertThat(updatedClient).isEqualTo(savedClient);
    }
    @Test
    void deleteTest() {
        // Given
        Client client = new Client("1", "Description","casa");
        Client savedClient = clientRepository.save(client);
        // When
        clientService.delete(savedClient.getId());
        // Then
        assertThat(clientService.getById(savedClient.getId())).isNull();
    }
}
