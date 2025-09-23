package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Support;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.SupportService;

import java.util.List;

@RestController
@RequestMapping("/api/supports")
public class SupportController {

    @Autowired
    private SupportService supportService;

    // Créer un support
    @PostMapping("/register")
    public Support register(@RequestBody Support support) {
        return supportService.createSupport(support);
    }

    // Récupérer tous les supports
    @GetMapping
    public List<Support> getAllSupports() {
        return supportService.getAllSupports();
    }

    // Récupérer un support par ID
    @GetMapping("/{id}")
    public Support getSupportById(@PathVariable Integer id) {
        return supportService.getSupportById(id);
    }

    // Mettre à jour un support
    @PutMapping("/{id}")
    public Support updateSupport(@PathVariable Integer id, @RequestBody Support support) {
        return supportService.updateSupport(id, support);
    }

    // Supprimer un support
    @DeleteMapping("/{id}")
    public String deleteSupport(@PathVariable Integer id) {
        supportService.deleteSupport(id);
        return "Support supprimé avec succès";
    }
}
