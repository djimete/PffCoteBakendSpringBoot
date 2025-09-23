package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    // Créer un client
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    // Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    // Récupérer un client par ID
    public Client getClientById(Integer id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
    }

    // Mettre à jour un client
    public Client updateClient(Integer id, Client client) {
        Client existing = clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        existing.setPrenom(client.getPrenom());
        existing.setNom(client.getNom());
        existing.setAdresse(client.getAdresse());
        existing.setTelephone(client.getTelephone());
        existing.setCompte(client.getCompte());
        existing.setPaiements(client.getPaiements());
        existing.setDemandes(client.getDemandes());
        existing.setServices(client.getServices());

        return clientRepo.save(existing);
    }

    // Supprimer un client
    public void deleteClient(Integer id) {
        if (!clientRepo.existsById(id)) {
            throw new RuntimeException("Client introuvable");
        }
        clientRepo.deleteById(id);
    }
}
