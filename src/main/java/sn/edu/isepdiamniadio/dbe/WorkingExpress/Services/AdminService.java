package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Admin;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    // Créer un admin
    public Admin createAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    // Récupérer tous les admins
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    // Récupérer un admin par ID
    public Admin getAdminById(Integer id) {
        return adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));
    }

    // Mettre à jour un admin
    public Admin updateAdmin(Integer id, Admin admin) {
        Admin existing = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));

        existing.setPrenom(admin.getPrenom());
        existing.setNom(admin.getNom());
        existing.setAdresse(admin.getAdresse());
        existing.setTelephone(admin.getTelephone());
        existing.setMotDePasse(admin.getMotDePasse());

        return adminRepo.save(existing);
    }

    // Supprimer un admin
    public void deleteAdmin(Integer id) {
        if (!adminRepo.existsById(id)) {
            throw new RuntimeException("Administrateur introuvable");
        }
        adminRepo.deleteById(id);
    }
}
