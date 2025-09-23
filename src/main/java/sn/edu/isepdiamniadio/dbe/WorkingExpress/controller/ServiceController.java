package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.ExpressService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    // Créer un service
    @PostMapping("/register")
    public ExpressService register(@RequestBody ExpressService service) {
        return serviceService.createService(service);
    }

    // Récupérer tous les services
    @GetMapping
    public List<ExpressService> getAllServices() {
        return serviceService.getAllServices();
    }

    // Récupérer un service par ID
    @GetMapping("/{id}")
    public ExpressService getServiceById(@PathVariable Integer id) {
        return serviceService.getServiceById(id);
    }

    // Mettre à jour un service
    @PutMapping("/{id}")
    public ExpressService updateService(@PathVariable Integer id, @RequestBody ExpressService service) {
        return serviceService.updateService(id, service);
    }

    // Supprimer un service
    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Integer id) {
        serviceService.deleteService(id);
        return "Service supprimé avec succès";
    }
}
