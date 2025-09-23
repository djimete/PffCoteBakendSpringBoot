package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Demande;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.DemandeService;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    // Créer une demande
    @PostMapping("/register")
    public String register(@RequestBody Demande demande) {
        demandeService.createDemande(demande);
        return "Demande enregistrée avec succès";
    }

    // Récupérer toutes les demandes
    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    // Récupérer une demande par ID
    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable Integer id) {
        return demandeService.getDemandeById(id);
    }

    // Mettre à jour une demande
    @PutMapping("/{id}")
    public String updateDemande(@PathVariable Integer id, @RequestBody Demande demande) {
        demandeService.updateDemande(id, demande);
        return "Demande mise à jour avec succès";
    }

    // Supprimer une demande
    @DeleteMapping("/{id}")
    public String deleteDemande(@PathVariable Integer id) {
        demandeService.deleteDemande(id);
        return "Demande supprimée avec succès";
    }
}
