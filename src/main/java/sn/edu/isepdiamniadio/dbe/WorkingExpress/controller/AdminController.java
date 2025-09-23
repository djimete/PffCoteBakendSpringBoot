package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Admin;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Créer un admin
    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        adminService.createAdmin(admin);
        return "Administrateur enregistré avec succès";
    }

    // Récupérer tous les admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Récupérer un admin par ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }

    // Mettre à jour un admin
    @PutMapping("/{id}")
    public String updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        adminService.updateAdmin(id, admin);
        return "Administrateur mis à jour avec succès";
    }

    // Supprimer un admin
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return "Administrateur supprimé avec succès";
    }
}
