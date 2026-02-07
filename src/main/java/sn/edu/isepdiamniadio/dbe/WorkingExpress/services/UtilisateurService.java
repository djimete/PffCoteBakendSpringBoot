package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.InscriptionRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.ValidationRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;

import java.util.*;

@Service
public class UtilisateurService {

    private final UtilisateurRepository repo;
    private final EmailService emailService;

    // Stockage temporaire des codes de validation
    private final Map<String, String> codesValidation = new HashMap<>();

    public UtilisateurService(UtilisateurRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    /**
     * ÉTAPE 1 : INSCRIPTION
     * Création de l'utilisateur (INACTIF) + envoi du code par email
     */
    public String inscrireUtilisateur(InscriptionRequest req) {

        if (repo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà !");
        }

        String code = String.valueOf(new Random().nextInt(900000) + 100000);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(req.getNom());
        utilisateur.setPrenom(req.getPrenom());
        utilisateur.setEmail(req.getEmail());
        utilisateur.setTelephone(req.getTelephone());
        utilisateur.setAdresse(req.getAdresse());
        utilisateur.setActif(false);

        repo.save(utilisateur); // ✅ INSERT propre

        codesValidation.put(req.getEmail(), code);

        emailService.envoyerEmail(
                req.getEmail(),
                "Code de validation",
                "Votre code de confirmation est : " + code
        );

        return code;
    }

    /**
     * ÉTAPE 2 : VALIDATION EMAIL
     * Vérifie le code et ACTIVE le compte
     */
    public Utilisateur validerEmail(ValidationRequest req) {

        String codeStocke = codesValidation.get(req.getEmail());

        if (codeStocke == null || !codeStocke.equals(req.getCode())) {
            throw new IllegalArgumentException("Code invalide ou expiré !");
        }

        Utilisateur utilisateur = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        utilisateur.setActif(true);
        codesValidation.remove(req.getEmail());

        return repo.save(utilisateur); // ✅ UPDATE
    }

    // CRUD
    public Utilisateur save(Utilisateur e) {
        if (repo.existsByEmail(e.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà !");
        }
        try {
            return repo.save(e);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erreur lors de l'enregistrement : " + ex.getMessage());
        }
    }

    public List<Utilisateur> findAll() {
        return repo.findAll();
    }

    public Utilisateur findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Map<String, String> getCodesValidation() {
        return codesValidation;
    }
}
