package com.exemple.service.impl;

import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.Client;
import com.exemple.entity.Commande;
import com.exemple.entity.Etat;
import com.exemple.entity.Produit;
import com.exemple.repository.InterfaceCommandeRepository;
import com.exemple.service.InterfaceClientService;
import com.exemple.service.InterfaceCommandeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@ExtendWith(SpringExtension.class)
class CommandeServiceTest {

    @Autowired
    private InterfaceCommandeService commandeService;
    @Autowired
    private InterfaceClientService clientService;
    Commande commande = new Commande();
    Client client = new Client();

    @BeforeAll
    static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    void setup() {
        System.out.println("Instantiating CommandeService");
/*
        client.setId(1L);
*/
        client.setName("hamza");
        client.setEmail("hamza@gm.com");
        client.setVille("casa");
        client = clientService.addClient(client);
        commande.setAddress_livraison("TK CASA");
        commande.setDate_creation(LocalDate.now());
        commande.setEtat_commande(Etat.EN_COURS);
        commande.setClient(clientService.getById(1L));
        commande=commandeService.addCommande(commande);
    }
    @AfterEach
    void tearDown() {
        System.out.println("Should Execute After Each Test");

        if(commandeService.getById(commande.getId()) !=null){
            commandeService.deleteCommandeById(commande.getId());
        }

        if(clientService.getById(client.getId()) !=null){
            clientService.delete(client.getId());
        }
    }
    @Test
    @DisplayName("Should Add Commande")
        void addCommande() {
            assertNotNull(commande);
            assertNotEquals(commandeService.getById(12L), commande);
        }


    @Test
    @DisplayName("Should Get Commande by ID")
    void getById() {

        Long id = commande.getId();

        Commande retrievedCommande = commandeService.getById(id);

        assertEquals(commande, retrievedCommande);
    }

    @Test
    @DisplayName("Should Get List of Commandes")
    void getCommandes() {


        List<Commande> commandes = commandeService.getCommandes();

        assertFalse(commandes.isEmpty());
        System.out.println(commandes);
    }

    @Test
    @DisplayName("Should Update Commande Status")
    void updateCommande() {


        commandeService.addCommande(commande);

        // Set the new status for updated Commande
        Etat newStatus = Etat.ANNULER;
        // Create a new Commande instance with the same ID and updated status
        Commande updatedCommande = new Commande();
        updatedCommande.setId(commande.getId());
        updatedCommande.setEtat_commande(newStatus);
        updatedCommande.setAddress_livraison("tk");
        updatedCommande.setDate_creation(LocalDate.now());
        updatedCommande.setClient(clientService.getById(1L));

        Commande resultCommande = commandeService.updateCommande(updatedCommande);

        // Check that only the status is updated
        assertEquals(commande.getId(), resultCommande.getId());
        assertEquals(newStatus, resultCommande.getEtat_commande());
    }



    @AfterAll
    static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
    }