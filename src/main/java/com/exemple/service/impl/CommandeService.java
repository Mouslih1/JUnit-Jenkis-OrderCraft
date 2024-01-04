package com.exemple.service.impl;

import com.exemple.entity.Commande;
import com.exemple.repository.InterfaceCommandeRepository;
import com.exemple.service.InterfaceCommandeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CommandeService implements InterfaceCommandeService {

    @Autowired
    private InterfaceCommandeRepository interfaceCommandeRepository;

    @Override
    public Commande addCommande(Commande commande)
    {
        return interfaceCommandeRepository.save(commande);
    }

    @Override
    public Commande getById(Long id)
    {
        return interfaceCommandeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commande> getCommandes()
    {
        return interfaceCommandeRepository.findAll();
    }

    @Override
    public Commande updateCommande(Commande commande)
    {
        return interfaceCommandeRepository.save(commande);
    }

    @Override
    public void deleteCommandeById(Long id)
    {
        interfaceCommandeRepository.deleteById(id);
    }
}
