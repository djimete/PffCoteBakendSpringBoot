package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Prestataire;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.PrestataireRepository;

import java.util.List;

@Service
public class PrestataireService {

    @Autowired
    private PrestataireRepository prestataireRepo;

    // Créer un prestataire
    public Prestataire createPrestataire(Prestataire prestataire) {
        return prestataireRepo.save(prestataire);
    }

    // Récupérer tous les prestataires
    public List<Prestataire> getAllPrestataires() {
        return prestataireRepo.findAll();
    }

    // Récupérer un prestataire par ID
    public Prestataire getPrestataireById(Integer id) {
        return prestataireRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestataire introuvable"));
    }

    // Mettre à jour un prestataire
    public Prestataire updatePrestataire(Integer id, Prestataire prestataire) {
        Prestataire existing = prestataireRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestataire introuvable"));

        existing.setNom(prestataire.getNom());
        existing.setPrenom(prestataire.getPrenom());
        existing.setAdresse(prestataire.getAdresse());
        existing.setTelephone(prestataire.getTelephone());
        existing.setCompte(prestataire.getCompte());
        existing.setServices(prestataire.getServices());
        existing.setDemandes(prestataire.getDemandes());

        return prestataireRepo.save(existing);
    }

    // Supprimer un prestataire
    public void deletePrestataire(Integer id) {
        if (!prestataireRepo.existsById(id)) {
            throw new RuntimeException("Prestataire introuvable");
        }
        prestataireRepo.deleteById(id);
    }
}
