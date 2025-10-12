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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Gestion des rôles", description = "API pour gérer les rôles utilisateurs")
public class RoleController {

    @Autowired
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Operation(summary = "Lister tous les rôles", description = "Retourne la liste complète des rôles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des rôles trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun rôle trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping
    public ResponseEntity getAllRoles() {
        try {
            List<Role> roles = service.findAll();
            if (roles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun rôle trouvé !");
            }
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des rôles : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Obtenir un rôle par ID", description = "Retourne un rôle spécifique grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Role.class),
                            examples = @ExampleObject(value = "{ \"id\": 1, \"nom\": \"ADMIN\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Rôle introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getRoleById(@Parameter(description = "Identifiant du rôle") @PathVariable Long id) {
        try {
            Role role = service.findById(id);
            if (role == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rôle introuvable !");
            }
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du rôle : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Créer un rôle", description = "Permet d’ajouter un nouveau rôle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rôle créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PostMapping
    public ResponseEntity createRole(@RequestBody Role e) {
        try {
            Role saved = service.save(e);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception ex) {
            System.out.println("Erreur lors de la création du rôle : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Mettre à jour un rôle", description = "Modifie un rôle existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Rôle introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateRole(@Parameter(description = "Identifiant du rôle à modifier")
                                     @PathVariable Long id,
                                     @RequestBody Role e) {
        try {
            e.setId(id);
            Role updated = service.save(e);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rôle introuvable !");
            }
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            System.out.println("Erreur lors de la mise à jour du rôle : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(summary = "Supprimer un rôle", description = "Supprime un rôle existant grâce à son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Rôle introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@Parameter(description = "Identifiant du rôle à supprimer") @PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Rôle supprimé avec succès !");
        } catch (Exception ex) {
            System.out.println("Erreur lors de la suppression du rôle : " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }
}
