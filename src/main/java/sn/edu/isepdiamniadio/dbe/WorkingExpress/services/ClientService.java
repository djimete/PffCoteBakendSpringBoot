package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // 🔹 Créer un client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // 🔹 Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // 🔹 Récupérer un client par ID
    public Client getClientById(Integer id) {
        return clientRepository.findById(id)
                .orElse(null);
    }

    // 🔹 Mettre à jour un client
    public Client updateClient(Integer id, Client clientDetails) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (!clientOpt.isPresent()) {
            throw new RuntimeException("Client avec id " + id + " non trouvé !");
        }
        Client client = clientOpt.get();
        client.setNom(clientDetails.getNom());
        client.setPrenom(clientDetails.getPrenom());
        client.setEmail(clientDetails.getEmail());
        client.setTelephone(clientDetails.getTelephone());
        return clientRepository.save(client);
    }

    // 🔹 Supprimer un client
    public void deleteClient(Integer id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (!clientOpt.isPresent()) {
            throw new RuntimeException("Client avec id " + id + " non trouvé !");
        }
        clientRepository.deleteById(id);
    }

    // 🔹 Rechercher un client par email (optionnel)
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
