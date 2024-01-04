package com.exemple.controller;

import com.exemple.entity.*;
import com.exemple.service.InterfaceClientService;
import com.exemple.service.InterfaceCommandeProduitsService;
import com.exemple.service.InterfaceCommandeService;
import com.exemple.service.InterfaceProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/commandes")
@SessionAttributes({"success"})
public class CommandeGestionController {
    @Autowired
    private InterfaceCommandeService interfaceCommandeService;
    @Autowired
    private InterfaceClientService interfaceClientService;
    @Autowired
    private InterfaceProduitService interfaceProduitService;
    @Autowired
    private InterfaceCommandeProduitsService interfaceCommandeProduitsService;

    @GetMapping
    public String go(Model model)
    {
        List<Client> clients = interfaceClientService.getClients();
        List<Etat> etats = Arrays.asList(Etat.values());
        List<Produit> produits = interfaceProduitService.getProduits();
        model.addAttribute("produits", produits);
        model.addAttribute("etats", etats);
        model.addAttribute("clients", clients);
        return "/commandes/commande_gestion";
    }

    @PostMapping("/add_commande")
    public String addCommande(@ModelAttribute("commande") Commande commande, Model model)
    {
        Client client = interfaceClientService.getById(commande.getClient().getId());
        commande.setClient(client);
        commande.setDate_creation(LocalDate.now());
        Commande c = interfaceCommandeService.addCommande(commande);
        model.addAttribute("commande", c);
        model.addAttribute("success", "Commande created successfully.");
        return "redirect:/commandes/create_info_produit/"+ c.getId();
    }

    @GetMapping("/create_info_produit/{id}")
    public String showCreateInfoForm(@PathVariable Long id, Model model, SessionStatus status)
    {
        List<Produit> produits = interfaceProduitService.getProduits();
        Commande commande = interfaceCommandeService.getById(id);
        model.addAttribute("commande", commande);
        model.addAttribute("produits", produits);
        model.addAttribute("commandeProduits", new CommandeProduits());
        status.setComplete();
        return "/commandes/info_produit";
    }

    @PostMapping("/add_info_produit")
    public String addProduitInfo(@ModelAttribute("commandeProduits") CommandeProduits commandeProduits, Long id_commande, Model model, RedirectAttributes redirectAttributes)
    {
        Produit produit = interfaceProduitService.getById(commandeProduits.getProduit().getId());
        Commande commande = interfaceCommandeService.getById(id_commande);
        commandeProduits.setCommande(commande);
        commandeProduits.setPrix_total(produit.getPrix_unitaire()*commandeProduits.getQuantite_commander());
        interfaceCommandeProduitsService.addCommandeProduits(commandeProduits);
        model.addAttribute("success", "Produit info created successfully.");
        return "redirect:/commandes/create_info_produit/" + commande.getId();
    }
}
