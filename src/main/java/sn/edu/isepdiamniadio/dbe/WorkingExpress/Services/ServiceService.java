package sn.edu.isepdiamniadio.dbe.WorkingExpress.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.ExpressService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.ExpressServiceRepository;

import java.util.List;

@Service
public class ServiceService {

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
                .orElseThrow(() -> new RuntimeException("Service introuvable avec ID : " + id));
    }

    // Mettre à jour un service
    public ExpressService updateService(Integer id, ExpressService updatedService) {
        ExpressService existing = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service introuvable avec ID : " + id));

        existing.setNom(updatedService.getNom());
        existing.setTypeService(updatedService.getTypeService());
        existing.setDescription(updatedService.getDescription());
        existing.setPrestataire(updatedService.getPrestataire());
        existing.setClients(updatedService.getClients());

        return serviceRepo.save(existing);
    }

    // Supprimer un service
    public void deleteService(Integer id) {
        if (!serviceRepo.existsById(id)) {
            throw new RuntimeException("Service introuvable avec ID : " + id);
        }
        serviceRepo.deleteById(id);
    }
}
