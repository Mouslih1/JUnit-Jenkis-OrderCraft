package com.exemple.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double prix_unitaire;
    private int quantity_in_stock;

    public Produit(String name, String description, double prix_unitaire, int quantity_in_stock) {
        this.name = name;
        this.description = description;
        this.prix_unitaire = prix_unitaire;
        this.quantity_in_stock = quantity_in_stock;
    }
}
