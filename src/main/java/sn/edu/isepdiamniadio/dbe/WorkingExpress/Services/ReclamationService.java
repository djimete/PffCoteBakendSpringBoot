package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Reclamation;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepo;

    // Créer une réclamation
    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepo.save(reclamation);
    }

    // Récupérer toutes les réclamations
    public List<Reclamation> getAllReclamations() {
        return reclamationRepo.findAll();
    }

    // Récupérer une réclamation par ID
    public Reclamation getReclamationById(Integer id) {
        return reclamationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation introuvable avec ID : " + id));
    }

    // Mettre à jour une réclamation
    public Reclamation updateReclamation(Integer id, Reclamation updatedReclamation) {
        Reclamation existing = reclamationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation introuvable avec ID : " + id));

        existing.setMessage(updatedReclamation.getMessage());
        existing.setDateReclamation(updatedReclamation.getDateReclamation());
        existing.setHeureReclamation(updatedReclamation.getHeureReclamation());
        existing.setStatusReclamation(updatedReclamation.getStatusReclamation());
        existing.setClient(updatedReclamation.getClient());

        return reclamationRepo.save(existing);
    }

    // Supprimer une réclamation
    public void deleteReclamation(Integer id) {
        if (!reclamationRepo.existsById(id)) {
            throw new RuntimeException("Réclamation introuvable avec ID : " + id);
        }
        reclamationRepo.deleteById(id);
    }
}
