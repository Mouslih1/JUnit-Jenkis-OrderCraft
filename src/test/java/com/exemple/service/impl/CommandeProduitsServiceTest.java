package com.exemple.service.impl;

import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.*;
import com.exemple.service.InterfaceClientService;
import com.exemple.service.InterfaceCommandeProduitsService;
import com.exemple.service.InterfaceCommandeService;
import com.exemple.service.InterfaceProduitService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@ExtendWith(SpringExtension.class)
class CommandeProduitsServiceTest {

    @Autowired
    InterfaceCommandeService interfaceCommandeService;

    @Autowired
    InterfaceProduitService interfaceProduitService;

    @Autowired
    InterfaceCommandeProduitsService interfaceCommandeProduitsService;

    @Autowired
    InterfaceClientService interfaceClientService;

    Client client = new Client();
    Commande commande = new Commande();
    CommandeProduits commandeProduits =  new CommandeProduits();
    Produit produit = new Produit();

    @BeforeAll
    static void setupAll()
    {
        System.out.println("------------- Before All -----------");
    }

    @BeforeEach
    void setUp() {
        System.out.println("before Each !");
        client.setName("marouane");
        client.setEmail("emal@email");
        client.setVille("casablanca");
        client = interfaceClientService.addClient(client);
        produit.setName("jordan shoes");
        produit.setDescription("description");
        produit.setPrix_unitaire(4500);
        produit.setQuantity_in_stock(5);
        produit = interfaceProduitService.addProduit(produit);
        commande.setClient(client);
        commande.setEtat_commande(Etat.EN_COURS);
        commande.setDate_creation(LocalDate.now());
        commande.setAddress_livraison("address livrison");
        commande = interfaceCommandeService.addCommande(commande);
        commandeProduits.setCommande(commande);
        commandeProduits.setQuantite_commander(2);
        commandeProduits.setProduit(produit);
        commandeProduits.setPrix_total(produit.getPrix_unitaire()*2);
        commandeProduits = interfaceCommandeProduitsService.addCommandeProduits(commandeProduits);
        System.out.println(commandeProduits);
    }

    @Test
    @DisplayName("Méthode add commades produits")
    void addCommandeProduits()
    {
        assertNotNull(commandeProduits);
        CommandeProduits cp = interfaceCommandeProduitsService.getById(commandeProduits.getId());
        assertEquals(commandeProduits.getId(), cp.getId());
    }

    @Test
    @DisplayName("Méthode get All commande produits")
    void getCommandeProduits()
    {

        List<CommandeProduits> commandeProduitsList = interfaceCommandeProduitsService.getCommandeProduits();
        System.out.println("-------------------------list");
        System.out.println(commandeProduitsList);
        assertFalse(commandeProduitsList.isEmpty());
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("-----------After Each------------");

        if (interfaceCommandeProduitsService.getById(commandeProduits.getId()) != null) {
            interfaceCommandeProduitsService.deleteCommandeProduitsById(commandeProduits.getId());
            System.out.println("deleting commande product successfully");
        }

        if(interfaceProduitService.getById(produit.getId()) != null)
        {
            interfaceProduitService.delete(produit.getId());
            System.out.println("deleting product successfully");
        }

        if (interfaceCommandeService.getById(commande.getId()) != null) {
            interfaceCommandeService.deleteCommandeById(commande.getId());
            System.out.println("deleting commande successfully");
        }

        if(interfaceClientService.getById(client.getId()) != null){
            interfaceClientService.delete(client.getId());
            System.out.println("deleting client successfully");
        }
    }

    @AfterAll
    static void tearDownAll()
    {
        System.out.println("after all the méthode");
    }
}