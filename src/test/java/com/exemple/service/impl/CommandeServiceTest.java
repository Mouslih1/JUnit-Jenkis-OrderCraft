package com.exemple.service.impl;

import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.Commande;
import com.exemple.entity.Etat;
import com.exemple.entity.Produit;
import com.exemple.repository.InterfaceCommandeRepository;
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
    private CommandeService commandeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProduitService produitService;

    @BeforeAll
    static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    void setup() {
        System.out.println("Instantiating CommandeService");
    }

    @Test
    @DisplayName("Should Add Commande")
        void addCommande() {
            System.out.println("add commande");
            Commande commande = new Commande();
            commande.setAddress_livraison("TK CASA");
            commande.setDate_creation(LocalDate.now());
            commande.setEtat_commande(Etat.EN_COURS);
            commande.setClient(clientService.getById(1L));
            System.out.println(commande);
            Commande c = commandeService.addCommande(commande);
            assertNotNull(c);
            assertNotEquals(commandeService.getById(13L), c);
        }


    @Test
    @DisplayName("Should Get Commande by ID")
    void getById() {

        Commande expectedCommande = new Commande();
        expectedCommande.setId(1L);
        expectedCommande.setAddress_livraison("TK CASA");
        expectedCommande.setDate_creation(LocalDate.now());
        expectedCommande.setClient(clientService.getById(1L));
        expectedCommande.setEtat_commande(Etat.EN_COURS);

        Long id = expectedCommande.getId();

        Commande retrievedCommande = commandeService.getById(id);

        assertEquals(expectedCommande, retrievedCommande);
    }

    @Test
    @DisplayName("Should Get List of Commandes")
    void getCommandes() {


        List<Commande> commandes = commandeService.getCommandes();

        assertFalse(commandes.isEmpty());

    }

    @Test
    @DisplayName("Should Update Commande Status")
    void updateCommande() {
        Commande existingCommande = new Commande();
        existingCommande.setAddress_livraison("tk");
        existingCommande.setDate_creation(LocalDate.now());
        existingCommande.setEtat_commande(Etat.EN_COURS);
        existingCommande.setClient(clientService.getById(1L));

        commandeService.addCommande(existingCommande);

        // Set the new status for updated Commande
        Etat newStatus = Etat.ANNULER;

        // Create a new Commande instance with the same ID and updated status
        Commande updatedCommande = new Commande();
        updatedCommande.setId(existingCommande.getId());
        updatedCommande.setEtat_commande(newStatus);
        updatedCommande.setAddress_livraison("tk");
        updatedCommande.setDate_creation(LocalDate.now());
        updatedCommande.setClient(clientService.getById(1L));

        Commande resultCommande = commandeService.updateCommande(updatedCommande);

        // Check that only the status is updated
        assertEquals(existingCommande.getId(), resultCommande.getId());
        assertEquals(newStatus, resultCommande.getEtat_commande());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
    }