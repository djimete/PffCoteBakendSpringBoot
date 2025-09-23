package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Compte;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.CompteService;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    // Créer un compte
    @PostMapping("/register")
    public String register(@RequestBody Compte compte) {
        compteService.createCompte(compte);
        return "Compte enregistré avec succès";
    }

    // Récupérer tous les comptes
    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    // Récupérer un compte par ID
    @GetMapping("/{id}")
    public Compte getCompteById(@PathVariable Integer id) {
        return compteService.getCompteById(id);
    }

    // Mettre à jour un compte
    @PutMapping("/{id}")
    public String updateCompte(@PathVariable Integer id, @RequestBody Compte compte) {
        compteService.updateCompte(id, compte);
        return "Compte mis à jour avec succès";
    }

    // Supprimer un compte
    @DeleteMapping("/{id}")
    public String deleteCompte(@PathVariable Integer id) {
        compteService.deleteCompte(id);
        return "Compte supprimé avec succès";
    }
}
