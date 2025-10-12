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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Reclamation;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.ReclamationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")
@Tag(name = "Gestion des réclamations", description = "API de gestion des réclamations (création, consultation, modification, suppression)")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @Operation(summary = "Créer une réclamation", description = "Permet d’ajouter une nouvelle réclamation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Réclamation créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<String> createReclamation(@RequestBody Reclamation reclamation) {
        try {
            reclamationService.createReclamation(reclamation);
            System.out.println("########## Nouvelle réclamation enregistrée ########");
            System.out.println("Réclamation : " + reclamation);
            return ResponseEntity.status(201).body("Réclamation enregistrée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’enregistrement : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Liste des réclamations", description = "Retourne la liste de toutes les réclamations enregistrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des réclamations trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune réclamation trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<?> getAllReclamations() {
        List<Reclamation> reclamations = reclamationService.getAllReclamations();
        System.out.println("########## Liste des réclamations ########");
        if (reclamations.isEmpty()) {
            return ResponseEntity.status(404).body("Aucune réclamation trouvée !");
        }
        return ResponseEntity.ok(reclamations);
    }

    @Operation(summary = "Détails d’une réclamation", description = "Retourne les informations d’une réclamation via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réclamation trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reclamation.class),
                            examples = @ExampleObject(value = "{ \"idReclamation\": 1, \"message\": \"Problème de livraison\", \"dateReclamation\": \"2025-10-08\", \"statusReclamation\": \"En cours\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Réclamation introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getReclamationById(@Parameter(description = "Identifiant de la réclamation") @PathVariable Integer id) {
        Optional<Reclamation> reclamation = Optional.ofNullable(reclamationService.getReclamationById(id));
        if (reclamation.isPresent()) {
            return ResponseEntity.ok(reclamation.get());
        }
        return ResponseEntity.status(404).body("Réclamation introuvable !");
    }

    @Operation(summary = "Modifier une réclamation", description = "Permet de modifier les informations d’une réclamation existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réclamation mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Réclamation introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReclamation(@Parameter(description = "Identifiant de la réclamation à modifier") @PathVariable Integer id,
                                                    @RequestBody Reclamation reclamation) {
        try {
            reclamationService.updateReclamation(id, reclamation);
            System.out.println("########## Mise à jour de la réclamation ########");
            System.out.println("Réclamation : " + reclamation);
            return ResponseEntity.ok("Réclamation mise à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }

    @Operation(summary = "Supprimer une réclamation", description = "Supprime une réclamation à partir de son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réclamation supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Réclamation introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReclamation(@Parameter(description = "Identifiant de la réclamation à supprimer") @PathVariable Integer id) {
        try {
            reclamationService.deleteReclamation(id);
            System.out.println("########## Suppression de la réclamation ########");
            System.out.println("Réclamation supprimée : " + id);
            return ResponseEntity.ok("Réclamation supprimée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }
}
