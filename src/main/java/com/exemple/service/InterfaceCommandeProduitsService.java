package com.exemple.service;

import com.exemple.entity.CommandeProduits;

import java.util.List;

public interface InterfaceCommandeProduitsService{
    CommandeProduits addCommandeProduits(CommandeProduits commandeProduits);
    List<CommandeProduits> getCommandeProduits();
    CommandeProduits getById(Long id);
}
