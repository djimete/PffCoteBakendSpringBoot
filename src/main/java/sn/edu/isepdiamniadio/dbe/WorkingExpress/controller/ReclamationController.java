package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Reclamation;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.ReclamationService;

import java.util.List;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    // Créer une réclamation
    @PostMapping("/register")
    public Reclamation register(@RequestBody Reclamation reclamation) {
        return reclamationService.createReclamation(reclamation);
    }

    // Récupérer toutes les réclamations
    @GetMapping
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    // Récupérer une réclamation par ID
    @GetMapping("/{id}")
    public Reclamation getReclamationById(@PathVariable Integer id) {
        return reclamationService.getReclamationById(id);
    }

    // Mettre à jour une réclamation
    @PutMapping("/{id}")
    public Reclamation updateReclamation(@PathVariable Integer id, @RequestBody Reclamation reclamation) {
        return reclamationService.updateReclamation(id, reclamation);
    }

    // Supprimer une réclamation
    @DeleteMapping("/{id}")
    public String deleteReclamation(@PathVariable Integer id) {
        reclamationService.deleteReclamation(id);
        return "Réclamation supprimée avec succès";
    }
}
