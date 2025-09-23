package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    // Créer un paiement
    @PostMapping("/register")
    public Paiement register(@RequestBody Paiement paiement) {
        return paiementService.createPaiement(paiement);
    }

    // Récupérer tous les paiements
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // Récupérer un paiement par ID
    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable Integer id) {
        return paiementService.getPaiementById(id);
    }

    // Mettre à jour un paiement
    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable Integer id, @RequestBody Paiement paiement) {
        return paiementService.updatePaiement(id, paiement);
    }

    // Supprimer un paiement
    @DeleteMapping("/{id}")
    public String deletePaiement(@PathVariable Integer id) {
        paiementService.deletePaiement(id);
        return "Paiement supprimé avec succès";
    }
}
