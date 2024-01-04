package com.exemple.service;

import com.exemple.entity.Commande;

import java.util.List;

public interface InterfaceCommandeService {
    Commande addCommande(Commande commande);
    Commande getById(Long id);
    List<Commande> getCommandes();
    Commande updateCommande(Commande commande);
    void deleteCommandeById(Long id);
}
