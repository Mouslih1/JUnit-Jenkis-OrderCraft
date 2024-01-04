package com.exemple.service.impl;

import com.exemple.config.DispatcherServletInit;
import com.exemple.config.PersistenceJPAConfig;
import com.exemple.entity.Produit;
import com.exemple.repository.InterfaceProduitRepository;
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
public class ProduitServiceTest {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private InterfaceProduitRepository produitRepository;

    @AfterEach
    void tearDown() {
        produitRepository.deleteAll();
    }
    @Test
    void getByIdExistingTest() {
        // Given
        Produit produit = new Produit("Produit1", "Description", 10.5, 20);
        Produit savedProduit = produitRepository.save(produit);
        // When
        Produit retrievedProduit = produitService.getById(savedProduit.getId());
        // Then
        assertThat(retrievedProduit).isEqualTo(savedProduit);
    }
    @Test
    void getByIdNonExistentTest() {
        // Given
        Long nonExistentId = 999L;
        // When
        Produit retrievedProduit = produitService.getById(nonExistentId);
        // Then
        assertThat(retrievedProduit).isNull();
    }
    @Test
    void getProduitsTest() {
        // Given
        Produit produit = new Produit("Produit1", "Description", 10.5, 20);
        produitRepository.save(produit);
        // When
        List<Produit> produits = produitService.getProduits();
        // Then
        assertThat(produits).hasSize(1);
        assertThat(produits.get(0)).isEqualTo(produit);
    }

    @Test
    void addProduitTest() {
        // Given
        Produit produit = new Produit("NewProduit", "Description", 15.75, 25);
        // When
        Produit savedProduit = produitService.addProduit(produit);
        // Then
        assertThat(savedProduit.getId()).isNotNull();
        assertThat(savedProduit).isEqualToIgnoringGivenFields(produit, "id");
    }
    @Test
    void updateProduitTest() {
        // Given
        Produit produit = new Produit("Produit1", "Description", 10.5, 20);
        Produit savedProduit = produitRepository.save(produit);
        // When
        savedProduit.setName("UpdatedProduit");
        savedProduit.setPrix_unitaire(25.0);
        Produit updatedProduit = produitService.updateProduit(savedProduit);
        // Then
        assertThat(updatedProduit).isEqualTo(savedProduit);
    }
    @Test
    void deleteTest() {
        // Given
        Produit produit = new Produit("Produit1", "Description", 10.5, 20);
        Produit savedProduit = produitRepository.save(produit);
        // When
        produitService.delete(savedProduit.getId());
        // Then
        assertThat(produitService.getById(savedProduit.getId())).isNull();
    }
}
