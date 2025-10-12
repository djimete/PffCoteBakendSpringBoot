package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Gestion des utilisateurs", description = "API pour gérer les utilisateurs")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @Operation(summary = "Lister tous les utilisateurs", description = "Retourne la liste complète des utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping
    public ResponseEntity getAllUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = service.findAll();
            if (utilisateurs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun utilisateur trouvé !");
            }
            return ResponseEntity.ok(utilisateurs);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Obtenir un utilisateur par ID", description = "Retourne un utilisateur spécifique grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class),
                            examples = @ExampleObject(value = "{ \"id\": 1, \"nom\": \"Dupont\", \"email\": \"dupont@example.com\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getUtilisateurById(@Parameter(description = "Identifiant de l’utilisateur") @PathVariable Long id) {
        try {
            Utilisateur utilisateur = service.findById(id);
            if (utilisateur == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable !");
            }
            return ResponseEntity.ok(utilisateur);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l’utilisateur : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Créer un utilisateur", description = "Permet d’ajouter un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PostMapping
    public ResponseEntity createUtilisateur(@RequestBody Utilisateur e) {
        try {
            Utilisateur saved = service.save(e);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception ex) {
            System.out.println("Erreur lors de la création de l’utilisateur : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Mettre à jour un utilisateur", description = "Modifie un utilisateur existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateUtilisateur(@Parameter(description = "Identifiant de l’utilisateur à modifier")
                                            @PathVariable Long id,
                                            @RequestBody Utilisateur e) {
        try {
            e.setId(id);
            Utilisateur updated = service.save(e);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable !");
            }
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            System.out.println("Erreur lors de la mise à jour de l’utilisateur : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Supprimer un utilisateur", description = "Supprime un utilisateur existant grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUtilisateur(@Parameter(description = "Identifiant de l’utilisateur à supprimer") @PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès !");
        } catch (Exception ex) {
            System.out.println("Erreur lors de la suppression de l’utilisateur : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }
}
