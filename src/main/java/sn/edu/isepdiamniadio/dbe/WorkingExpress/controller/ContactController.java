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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Contact;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.ContactService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Gestion des contacts", description = "API de gestion des contacts (création, consultation, modification, suppression)")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Operation(
            summary = "Créer un contact",
            description = "Permet d’ajouter un nouveau contact"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity createContact(@RequestBody Contact contact) {
        try {
            contactService.createContact(contact);
            System.out.println("########## Nouveau contact enregistré ########");
            System.out.println("Contact : " + contact);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contact enregistré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’enregistrement : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Liste des contacts",
            description = "Retourne la liste de tous les contacts enregistrés"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des contacts trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun contact trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        System.out.println("########## Liste des contacts ########");
        if (contacts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun contact trouvé !");
        }
        return ResponseEntity.ok(contacts);
    }

    @Operation(
            summary = "Détails d’un contact",
            description = "Retourne les informations d’un contact via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class),
                            examples = @ExampleObject(value = "{ \"idContact\": 1, \"contenu\": \"Demande d'informations\", \"dateContact\": \"2025-10-08\", \"motif\": \"Support technique\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Contact introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getContactById(@Parameter(description = "Identifiant du contact") @PathVariable Integer id) {
        Optional<Contact> contact = Optional.ofNullable(contactService.getContactById(id));
        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact introuvable !");
    }

    @Operation(
            summary = "Modifier un contact",
            description = "Permet de modifier les informations d’un contact existant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Contact introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateContact(@Parameter(description = "Identifiant du contact à modifier") @PathVariable Integer id,
                                        @RequestBody Contact contact) {
        try {
            contactService.updateContact(id, contact);
            System.out.println("########## Mise à jour du contact ########");
            System.out.println("Contact : " + contact);
            return ResponseEntity.ok("Contact mis à jour avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }

    @Operation(
            summary = "Supprimer un contact",
            description = "Supprime un contact à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Contact introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteContact(@Parameter(description = "Identifiant du contact à supprimer") @PathVariable Integer id) {
        try {
            contactService.deleteContact(id);
            System.out.println("########## Suppression du contact ########");
            System.out.println("Contact supprimé : " + id);
            return ResponseEntity.ok("Contact supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }
}
