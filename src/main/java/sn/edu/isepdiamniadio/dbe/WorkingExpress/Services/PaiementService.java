package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.PaiementRepository;

import java.util.List;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepo;

    // Créer un paiement
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }

    // Récupérer tous les paiements
    public List<Paiement> getAllPaiements() {
        return paiementRepo.findAll();
    }

    // Récupérer un paiement par ID
    public Paiement getPaiementById(Integer id) {
        return paiementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec ID : " + id));
    }

    // Mettre à jour un paiement
    public Paiement updatePaiement(Integer id, Paiement updatedPaiement) {
        Paiement existing = paiementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec ID : " + id));

        existing.setModePaiement(updatedPaiement.getModePaiement());
        existing.setMontant(updatedPaiement.getMontant());
        existing.setDatePaiement(updatedPaiement.getDatePaiement());
        existing.setHeurePaiement(updatedPaiement.getHeurePaiement());
        existing.setClient(updatedPaiement.getClient());

        return paiementRepo.save(existing);
    }

    // Supprimer un paiement
    public void deletePaiement(Integer id) {
        if (!paiementRepo.existsById(id)) {
            throw new RuntimeException("Paiement introuvable avec ID : " + id);
        }
        paiementRepo.deleteById(id);
    }
}
