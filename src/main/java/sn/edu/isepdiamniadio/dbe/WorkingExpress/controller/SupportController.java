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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Support;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.SupportService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supports")
@Tag(name = "Gestion des supports", description = "API de gestion des supports (création, consultation, modification, suppression)")
public class SupportController {

    @Autowired
    private SupportService supportService;

    @Operation(
            summary = "Créer un support",
            description = "Permet d’ajouter un nouveau support"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Support créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity createSupport(@RequestBody Support support) {
        try {
            supportService.createSupport(support);
            System.out.println("########## Nouveau support enregistré ########");
            System.out.println("Support : " + support);
            return ResponseEntity.status(201).body("Support enregistré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’enregistrement : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Liste des supports",
            description = "Retourne la liste de tous les supports enregistrés"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des supports trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun support trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity getAllSupports() {
        List<Support> supports = supportService.getAllSupports();
        System.out.println("########## Liste des supports ########");
        if (supports.isEmpty()) {
            return ResponseEntity.status(404).body("Aucun support trouvé !");
        }
        return ResponseEntity.ok(supports);
    }

    @Operation(
            summary = "Détails d’un support",
            description = "Retourne les informations d’un support via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Support trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Support.class),
                            examples = @ExampleObject(value = "{ \"idSupport\": 1, \"typeSupport\": \"Téléphonique\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Support introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getSupportById(@Parameter(description = "Identifiant du support") @PathVariable Integer id) {
        Optional<Support> support = Optional.ofNullable(supportService.getSupportById(id));
        if (support.isPresent()) {
            return ResponseEntity.ok(support.get());
        }
        return ResponseEntity.status(404).body("Support introuvable !");
    }

    @Operation(
            summary = "Modifier un support",
            description = "Permet de modifier les informations d’un support existant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Support mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Support introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateSupport(@Parameter(description = "Identifiant du support à modifier") @PathVariable Integer id,
                                        @RequestBody Support support) {
        try {
            supportService.updateSupport(id, support);
            System.out.println("########## Mise à jour du support ########");
            System.out.println("Support : " + support);
            return ResponseEntity.ok("Support mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Supprimer un support",
            description = "Supprime un support à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Support supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Support introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteSupport(@Parameter(description = "Identifiant du support à supprimer") @PathVariable Integer id) {
        try {
            supportService.deleteSupport(id);
            System.out.println("########## Suppression du support ########");
            System.out.println("Support supprimé : " + id);
            return ResponseEntity.ok("Support supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(500).body("Erreur interne !");
        }
    }
}
