package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repo;

    public UtilisateurService(UtilisateurRepository repo) {
        this.repo = repo;
    }

    public Utilisateur save(Utilisateur e) {
        return repo.save(e);
    }

    public List<Utilisateur> findAll() {
        return repo.findAll();
    }

    public Utilisateur findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


}