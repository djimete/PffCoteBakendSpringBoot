package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Demande;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.DemandeRepository;

import java.util.List;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepo;

    // Créer une demande
    public Demande createDemande(Demande demande) {
        return demandeRepo.save(demande);
    }

    // Récupérer toutes les demandes
    public List<Demande> getAllDemandes() {
        return demandeRepo.findAll();
    }

    // Récupérer une demande par ID
    public Demande getDemandeById(Integer id) {
        return demandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));
    }

    // Mettre à jour une demande
    public Demande updateDemande(Integer id, Demande demande) {
        Demande existing = demandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));

        existing.setMessage(demande.getMessage());
        existing.setStatusDemande(demande.getStatusDemande());

        return demandeRepo.save(existing);
    }

    // Supprimer une demande
    public void deleteDemande(Integer id) {
        if (!demandeRepo.existsById(id)) {
            throw new RuntimeException("Demande introuvable");
        }
        demandeRepo.deleteById(id);
    }
}
