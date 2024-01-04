package com.exemple.controller;

import com.exemple.entity.*;
import com.exemple.service.InterfaceClientService;
import com.exemple.service.InterfaceCommandeProduitsService;
import com.exemple.service.InterfaceCommandeService;
import com.exemple.service.InterfaceProduitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/situation_commandes")
@SessionAttributes({"success"})
public class SituationCommandesController {

    private InterfaceCommandeProduitsService interfaceCommandeProduitsService;
    private InterfaceCommandeService interfaceCommandeService;
    private InterfaceProduitService interfaceProduitService;

    @GetMapping
    public String situtationCommandes(Model model, SessionStatus status) {
        List<CommandeProduits> commandeProduits = interfaceCommandeProduitsService.getCommandeProduits();
        model.addAttribute("etat", Etat.values());
        model.addAttribute("commandeProduits", commandeProduits);
        status.setComplete();
        return "/situation_commande/situation_commande";
    }

    @GetMapping("/edit_status/{id}")
    public String showEditStatusForm(@PathVariable Long id, Model model) {
        List<CommandeProduits> commandeProduits = interfaceCommandeProduitsService.getCommandeProduits();
        List<Produit> produits = interfaceProduitService.getProduits();
        Commande commande = interfaceCommandeService.getById(id);
        System.out.println(commande);
        List<Etat> etats = Arrays.asList(Etat.values());
        model.addAttribute("etats", etats);
        model.addAttribute("commandeProduits", commandeProduits);
        model.addAttribute("commande", commande);
        model.addAttribute("produits", produits);
        return "/situation_commande/edit_status";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("commande") Commande commande, Model model) {
        Commande c = interfaceCommandeService.getById(commande.getId());
        commande.setDate_creation(c.getDate_creation());
        interfaceCommandeService.updateCommande(commande);
        model.addAttribute("success", "Status updated successfully.");
        return "redirect:/situation_commandes";
    }
}
