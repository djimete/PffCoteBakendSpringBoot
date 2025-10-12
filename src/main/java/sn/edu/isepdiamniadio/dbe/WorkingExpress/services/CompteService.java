package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Compte;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.CompteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    // Créer ou mettre à jour un compte
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // Récupérer tous les comptes
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // Récupérer un compte par ID
    public Optional<Compte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }

    // Supprimer un compte par ID
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}
