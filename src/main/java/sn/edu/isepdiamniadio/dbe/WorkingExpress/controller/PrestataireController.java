package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Prestataire;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.PrestataireService;

import java.util.List;

@RestController
@RequestMapping("/api/prestataires")
public class PrestataireController {

    @Autowired
    private PrestataireService prestataireService;

    // Créer un prestataire
    @PostMapping("/register")
    public String register(@RequestBody Prestataire prestataire) {
        prestataireService.createPrestataire(prestataire);
        return "Prestataire enregistré avec succès";
    }

    // Récupérer tous les prestataires
    @GetMapping
    public List<Prestataire> getAllPrestataires() {
        return prestataireService.getAllPrestataires();
    }

    // Récupérer un prestataire par ID
    @GetMapping("/{id}")
    public Prestataire getPrestataireById(@PathVariable Integer id) {
        return prestataireService.getPrestataireById(id);
    }

    // Mettre à jour un prestataire
    @PutMapping("/{id}")
    public String updatePrestataire(@PathVariable Integer id, @RequestBody Prestataire prestataire) {
        prestataireService.updatePrestataire(id, prestataire);
        return "Prestataire mis à jour avec succès";
    }

    // Supprimer un prestataire
    @DeleteMapping("/{id}")
    public String deletePrestataire(@PathVariable Integer id) {
        prestataireService.deletePrestataire(id);
        return "Prestataire supprimé avec succès";
    }
}
