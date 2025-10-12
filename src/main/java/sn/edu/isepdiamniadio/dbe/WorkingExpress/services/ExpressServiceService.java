package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.ExpressService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ExpressServiceRepository;

import java.util.List;

@Service
public class ExpressServiceService {

    @Autowired
    private ExpressServiceRepository serviceRepo;

    // Créer un service
    public ExpressService createService(ExpressService service) {
        return serviceRepo.save(service);
    }

    // Récupérer tous les services
    public List<ExpressService> getAllServices() {
        return serviceRepo.findAll();
    }

    // Récupérer un service par ID
    public ExpressService getServiceById(Integer id) {
        return serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service introuvable"));
    }

    // Mettre à jour un service
    public ExpressService updateService(Integer id, ExpressService service) {
        ExpressService existing = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service introuvable"));

        existing.setNomService(service.getNomService());
        existing.setTypeService(service.getTypeService());
        existing.setDescription(service.getDescription());

        return serviceRepo.save(existing);
    }

    // Supprimer un service
    public void deleteService(Integer id) {
        if (!serviceRepo.existsById(id)) {
            throw new RuntimeException("Service introuvable");
        }
        serviceRepo.deleteById(id);
    }
}
