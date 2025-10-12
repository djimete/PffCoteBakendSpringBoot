package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }

    public Role save(Role e) {
        return repo.save(e);
    }

    public List<Role> findAll() {
        return repo.findAll();
    }

    public Role findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Role introuvable"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}