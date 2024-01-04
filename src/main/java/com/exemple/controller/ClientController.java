package com.exemple.controller;

import com.exemple.entity.Client;
import com.exemple.service.InterfaceClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private InterfaceClientService interfaceClientService;

    @GetMapping
    public String getAllClients(Model model)
    {
        List<Client> clients = interfaceClientService.getClients();
        model.addAttribute("clients", clients);
        return "/clients/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model)
    {
        model.addAttribute("client", new Client());
        return "/clients/create";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute("client") Client client)
    {
        interfaceClientService.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model)
    {
        Client client = interfaceClientService.getById(id);
        model.addAttribute("client", client);
        return "/clients/edit";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute("client") Client client)
    {
        interfaceClientService.updateClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id)
    {
        interfaceClientService.delete(id);
        return "redirect:/clients";
    }

}
