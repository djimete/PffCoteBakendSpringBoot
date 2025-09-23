package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Compte;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.CompteRepository;

import java.util.List;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepo;

    // Créer un compte
    public Compte createCompte(Compte compte) {
        return compteRepo.save(compte);
    }

    // Récupérer tous les comptes
    public List<Compte> getAllComptes() {
        return compteRepo.findAll();
    }

    // Récupérer un compte par ID
    public Compte getCompteById(Integer id) {
        return compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    // Mettre à jour un compte
    public Compte updateCompte(Integer id, Compte compte) {
        Compte existing = compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        existing.setLogin(compte.getLogin());
        existing.setMotPasse(compte.getMotPasse());
        existing.setClient(compte.getClient());
        existing.setPrestataire(compte.getPrestataire());

        return compteRepo.save(existing);
    }

    // Supprimer un compte
    public void deleteCompte(Integer id) {
        if (!compteRepo.existsById(id)) {
            throw new RuntimeException("Compte introuvable");
        }
        compteRepo.deleteById(id);
    }
}
