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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Prestataire;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.PrestataireService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestataires")
@Tag(name = "Gestion des prestataires", description = "API de gestion des prestataires (création, consultation, modification, suppression)")
public class PrestataireController {

    @Autowired
    private PrestataireService prestataireService;

    @Operation(
            summary = "Créer un prestataire",
            description = "Permet d’ajouter un nouveau prestataire"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestataire créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity createPrestataire(@RequestBody Prestataire prestataire) {
        try {
            prestataireService.createPrestataire(prestataire);
            System.out.println("########## Nouveau prestataire enregistré ########");
            System.out.println("Prestataire : " + prestataire);
            return ResponseEntity.status(201).body("Prestataire enregistré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’enregistrement : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Liste des prestataires",
            description = "Retourne la liste de tous les prestataires enregistrés"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des prestataires trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun prestataire trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity getAllPrestataires() {
        List<Prestataire> prestataires = prestataireService.getAllPrestataires();
        System.out.println("########## Liste des prestataires ########");
        if (prestataires.isEmpty()) {
            return ResponseEntity.status(404).body("Aucun prestataire trouvé !");
        }
        return ResponseEntity.ok(prestataires);
    }

    @Operation(
            summary = "Détails d’un prestataire",
            description = "Retourne les informations d’un prestataire via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestataire trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Prestataire.class),
                            examples = @ExampleObject(value = "{ \"id\": 1, \"nom\": \"Sow\", \"email\": \"sow@gmail.com\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Prestataire introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getPrestataireById(@Parameter(description = "Identifiant du prestataire") @PathVariable Integer id) {
        Optional<Prestataire> prestataire = Optional.ofNullable(prestataireService.getPrestataireById(id));
        if (prestataire.isPresent()) {
            return ResponseEntity.ok(prestataire.get());
        }
        return ResponseEntity.status(404).body("Prestataire introuvable !");
    }

    @Operation(
            summary = "Modifier un prestataire",
            description = "Permet de modifier les informations d’un prestataire existant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestataire mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Prestataire introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updatePrestataire(@Parameter(description = "Identifiant du prestataire à modifier") @PathVariable Integer id,
                                            @RequestBody Prestataire prestataire) {
        try {
            prestataireService.updatePrestataire(id, prestataire);
            System.out.println("########## Mise à jour du prestataire ########");
            System.out.println("Prestataire : " + prestataire);
            return ResponseEntity.ok("Prestataire mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Supprimer un prestataire",
            description = "Supprime un prestataire à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestataire supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Prestataire introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletePrestataire(@Parameter(description = "Identifiant du prestataire à supprimer") @PathVariable Integer id) {
        try {
            prestataireService.deletePrestataire(id);
            System.out.println("########## Suppression du prestataire ########");
            System.out.println("Prestataire supprimé : " + id);
            return ResponseEntity.ok("Prestataire supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }
}
