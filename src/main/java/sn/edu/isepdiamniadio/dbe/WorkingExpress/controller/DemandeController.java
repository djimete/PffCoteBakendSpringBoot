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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Demande;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.DemandeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandes")
@Tag(name = "Gestion des demandes", description = "API de gestion des demandes (création, consultation, modification, suppression)")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @Operation(
            summary = "Créer une demande",
            description = "Permet d’ajouter une nouvelle demande"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Demande créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<String> createDemande(@RequestBody Demande demande) {
        try {
            demandeService.createDemande(demande);
            System.out.println("########## Nouvelle demande enregistrée ########");
            System.out.println("Demande : " + demande);
            return ResponseEntity.status(201).body("Demande enregistrée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’enregistrement : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Liste des demandes",
            description = "Retourne la liste de toutes les demandes enregistrées"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des demandes trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune demande trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<?> getAllDemandes() {
        List<Demande> demandes = demandeService.getAllDemandes();
        System.out.println("########## Liste des demandes ########");
        if (demandes.isEmpty()) {
            return ResponseEntity.status(404).body("Aucune demande trouvée !");
        }
        return ResponseEntity.ok(demandes);
    }

    @Operation(
            summary = "Détails d’une demande",
            description = "Retourne les informations d’une demande via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demande trouvée",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Demande.class),
                            examples = @ExampleObject(value = "{ \"idDemande\": 1, \"message\": \"Réparer plomberie\", \"statusDemande\": \"En attente\" }")
                    )),
            @ApiResponse(responseCode = "404", description = "Demande introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getDemandeById(
            @Parameter(description = "Identifiant de la demande") @PathVariable Integer id) {
        Optional<Demande> demande = Optional.ofNullable(demandeService.getDemandeById(id));
        if (demande.isPresent()) {
            return ResponseEntity.ok(demande.get());
        }
        return ResponseEntity.status(404).body("Demande introuvable !");
    }

    @Operation(
            summary = "Modifier une demande",
            description = "Permet de modifier les informations d’une demande existante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demande mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Demande introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDemande(
            @Parameter(description = "Identifiant de la demande à modifier") @PathVariable Integer id,
            @RequestBody Demande demande) {
        try {
            demandeService.updateDemande(id, demande);
            System.out.println("########## Mise à jour de la demande ########");
            System.out.println("Demande : " + demande);
            return ResponseEntity.ok("Demande mise à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Supprimer une demande",
            description = "Supprime une demande à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Demande supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Demande introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDemande(
            @Parameter(description = "Identifiant de la demande à supprimer") @PathVariable Integer id) {
        try {
            demandeService.deleteDemande(id);
            System.out.println("########## Suppression de la demande ########");
            System.out.println("Demande supprimée : " + id);
            return ResponseEntity.ok("Demande supprimée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }
}
