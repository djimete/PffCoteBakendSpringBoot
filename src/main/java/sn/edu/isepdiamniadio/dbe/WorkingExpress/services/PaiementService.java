package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ClientRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.PaiementRepository;

import java.util.List;

@Service
@Transactional
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final ClientRepository clientRepository;

    public PaiementService(PaiementRepository paiementRepository, ClientRepository clientRepository) {
        this.paiementRepository = paiementRepository;
        this.clientRepository = clientRepository;
    }

    // Récupérer tous les paiements
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    // Récupérer un paiement par ID
    public Paiement findById(Integer id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }

    // Créer un paiement
    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    // Créer un paiement lié à un client
    public Paiement createPaiement(Paiement paiement, Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        paiement.setClient(client);
        return paiementRepository.save(paiement);
    }

    // Mettre à jour un paiement
    public Paiement update(Integer id, Paiement paiement) {
        Paiement existing = findById(id);

        existing.setModePaiement(paiement.getModePaiement());
        existing.setMontant(paiement.getMontant());
        existing.setDatePaiement(paiement.getDatePaiement());

        // Mettre à jour le client si nécessaire
        if (paiement.getClient() != null) {
            Client client = clientRepository.findById(paiement.getClient().getIdClient())
                    .orElseThrow(() -> new RuntimeException("Client introuvable"));
            existing.setClient(client);
        }

        return paiementRepository.save(existing);
    }

    // Supprimer un paiement
    public void delete(Integer id) {
        Paiement paiement = findById(id);
        paiementRepository.delete(paiement);
    }
}
