package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.ExpressService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.ExpressServiceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@Tag(name = "Gestion des services", description = "API pour gérer les services express")
public class ExpressServiceController {

    @Autowired
    private ExpressServiceService service;

    @Operation(summary = "Créer un service", description = "Ajoute un nouveau service express")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PostMapping
    public ResponseEntity createService(@RequestBody ExpressService expressService) {
        try {
            service.createService(expressService);
            System.out.println("########## Nouveau service enregistré ########");
            System.out.println("Service : " + expressService);
            return ResponseEntity.status(HttpStatus.CREATED).body("Service enregistré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du service : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Lister tous les services", description = "Retourne la liste complète des services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des services trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun service trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping
    public ResponseEntity getAllServices() {
        List<ExpressService> services = service.getAllServices();
        System.out.println("########## Liste des services ########");
        if (services.isEmpty()) {
            return ResponseEntity.status(404).body("Aucun service trouvé !");
        }
        return ResponseEntity.ok(services);
    }

    @Operation(summary = "Obtenir un service par ID", description = "Retourne un service spécifique via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExpressService.class),
                            examples = @ExampleObject(value = "{ \"idService\": 1, \"nomService\": \"Livraison\", \"typeService\": \"Express\", \"description\": \"Livraison rapide\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Service introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getServiceById(@Parameter(description = "Identifiant du service") @PathVariable Integer id) {
        Optional<ExpressService> serviceOptional = Optional.ofNullable(service.getServiceById(id));
        if (serviceOptional.isPresent()) {
            return ResponseEntity.ok(serviceOptional.get());
        }
        return ResponseEntity.status(404).body("Service introuvable !");
    }

    @Operation(summary = "Modifier un service", description = "Met à jour un service existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Service introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateService(@Parameter(description = "Identifiant du service à modifier") @PathVariable Integer id,
                                        @RequestBody ExpressService expressService) {
        try {
            ExpressService updated = service.updateService(id, expressService);
            if (updated == null) {
                return ResponseEntity.status(404).body("Service introuvable !");
            }
            System.out.println("########## Service mis à jour ########");
            System.out.println("Service : " + updated);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour du service : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Supprimer un service", description = "Supprime un service existant via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Service introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@Parameter(description = "Identifiant du service à supprimer") @PathVariable Integer id) {
        try {
            service.deleteService(id);
            System.out.println("########## Service supprimé ########");
            System.out.println("ID : " + id);
            return ResponseEntity.ok("Service supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression du service : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }
}
