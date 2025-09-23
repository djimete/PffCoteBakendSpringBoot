package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Créer un client
    @PostMapping("/register")
    public String register(@RequestBody Client client) {
        clientService.createClient(client);
        return "Client enregistré avec succès";
    }

    // Récupérer tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Récupérer un client par ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    // Mettre à jour un client
    @PutMapping("/{id}")
    public String updateClient(@PathVariable Integer id, @RequestBody Client client) {
        clientService.updateClient(id, client);
        return "Client mis à jour avec succès";
    }

    // Supprimer un client
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "Client supprimé avec succès";
    }
}
