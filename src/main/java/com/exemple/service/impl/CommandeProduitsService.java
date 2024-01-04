package com.exemple.service.impl;

import com.exemple.entity.CommandeProduits;
import com.exemple.repository.InterfaceCommandeProduitRepository;
import com.exemple.service.InterfaceCommandeProduitsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeProduitsService implements InterfaceCommandeProduitsService {

    private InterfaceCommandeProduitRepository interfaceCommandeProduitRepository;

    @Override
    public CommandeProduits addCommandeProduits(CommandeProduits commandeProduits)
    {
        return interfaceCommandeProduitRepository.save(commandeProduits);
    }

    @Override
    public List<CommandeProduits> getCommandeProduits() {
        return interfaceCommandeProduitRepository.findAll();
    }

    @Override
    public CommandeProduits getById(Long id)
    {
        return interfaceCommandeProduitRepository.findById(id).orElse(null);
    }
}
