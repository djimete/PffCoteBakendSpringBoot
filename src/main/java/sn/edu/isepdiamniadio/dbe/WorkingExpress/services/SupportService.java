package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Support;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.SupportRepository;

import java.util.List;

@Service
public class SupportService {

    @Autowired
    private SupportRepository supportRepo;

    // Créer un support
    public Support createSupport(Support support) {
        return supportRepo.save(support);
    }

    // Récupérer tous les supports
    public List<Support> getAllSupports() {
        return supportRepo.findAll();
    }

    // Récupérer un support par ID
    public Support getSupportById(Integer id) {
        return supportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Support introuvable"));
    }

    // Mettre à jour un support
    public Support updateSupport(Integer id, Support support) {
        Support existing = supportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Support introuvable"));

        existing.setTypeSupport(support.getTypeSupport());

        return supportRepo.save(existing);
    }

    // Supprimer un support
    public void deleteSupport(Integer id) {
        if (!supportRepo.existsById(id)) {
            throw new RuntimeException("Support introuvable");
        }
        supportRepo.deleteById(id);
    }
}
