package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Utilisateur> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur e) {
        return service.save(e);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur e) {
        e.setId(id);
        return service.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}