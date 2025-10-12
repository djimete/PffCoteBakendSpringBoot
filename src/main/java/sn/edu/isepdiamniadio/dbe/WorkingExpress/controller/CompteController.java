package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Compte;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.CompteService;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@Tag(name = "Gestion des comptes", description = "API pour gérer les comptes (ajout, modification, suppression, consultation)")
public class CompteController {

    @Autowired
    private CompteService compteService;

    // Créer un compte
    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteService.saveCompte(compte);
        return ResponseEntity.ok(savedCompte);
    }

    // Récupérer tous les comptes
    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        return ResponseEntity.ok(compteService.getAllComptes());
    }

    // Récupérer un compte par ID
    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour un compte
    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compteDetails) {
        return compteService.getCompteById(id).map(compte -> {
            compte.setMotPasse(compteDetails.getMotPasse());
            Compte updatedCompte = compteService.saveCompte(compte);
            return ResponseEntity.ok(updatedCompte);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un compte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        if (compteService.getCompteById(id).isPresent()) {
            compteService.deleteCompte(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
