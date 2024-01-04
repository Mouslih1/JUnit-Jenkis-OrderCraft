package com.exemple.service.impl;

import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.Client;
import com.exemple.entity.Commande;
import com.exemple.entity.CommandeProduits;
import com.exemple.entity.Produit;
import com.exemple.service.InterfaceCommandeProduitsService;
import com.exemple.service.InterfaceCommandeService;
import com.exemple.service.InterfaceProduitService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

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

    @BeforeAll
    static void setupAll()
    {
        System.out.println("Before All");
    }

    @BeforeEach
    void setUp() {
        System.out.println("before Each !");
    }

    @Test
    @DisplayName("Méthode add commades produits")
    void addCommandeProduits()
    {
        CommandeProduits commandeProduits = new CommandeProduits();
        Produit produit = interfaceProduitService.getById(6L);
        Commande commande = interfaceCommandeService.getById(1L);
        commandeProduits.setCommande(commande);
        commandeProduits.setProduit(produit);
        commandeProduits.setQuantite_commander(3);
        commandeProduits.setPrix_total(produit.getPrix_unitaire()*3);
        CommandeProduits cp = interfaceCommandeProduitsService.addCommandeProduits(commandeProduits);
        assertNotEquals(interfaceCommandeProduitsService.getById(2L), cp);
        assertNotNull(cp);
    }

    @Test
    @DisplayName("Méthode get All commande produits")
    void getCommandeProduits()
    {
        List<CommandeProduits> commandeProduitsList = interfaceCommandeProduitsService.getCommandeProduits();
        assertFalse(commandeProduitsList.isEmpty());
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("After Each");
    }

    @AfterAll
    static void tearDownAll()
    {
        System.out.println("after all the méthode");
    }
}